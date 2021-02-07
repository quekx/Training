---
title: 学习笔记(6):HBase
date: 2020-11-12 16:01:31
tags:
---

> HBase(hadoop database)是大数据处理中常见的存储，底层基于 HDFS，支持机器资源水平扩展，上百亿数据存储，可以作为学习分布式数据库的入门级数据库



#### 使用

HBase 的数据存储为 KV 结构

* 数据 rowKey 为主键，由使用者设计，rowKey 可以唯一确定到一条记录
* 记录的字段列可以水平扩展，一条记录的字段可以任意添加，不同的记录字段可以不一致（需要啥加啥），这和 HBase 的设计和列存储方式有关。和关系型数据库不同，关系型数据库所有数据都有统一确定的 schema，HBase 无强 schema 约束，对于 HBase 一条记录，列字段可以任务扩展，只会存储存在的列字段。



#### 架构

HBase 整体上由以下四部分组成：

* Zookeeper：负责整个集群的高可用，选举 Master 节点；保存集群信息
* Master：HBase 管理节点，管理 Region Server 分配 Region，数据 Region 切分；均衡负载；创建表DDL；存储 hbase:meta 信息等
* Region Server：管理 Region，负责实际的数据操作读写，Region 是 HBase 中的数据存储单元。Client 直接和 Region Server 直连进行交互
* HDFS：实际底层的数据物理存储

<!-- more -->

{% asset_img hbase.jpg hbase %}


##### 1.1 存储结构

一条完整的记录在 HBase 中逻辑上还是以一行来存在，以唯一主键 rowKey 定位到唯一一条记录。HBase 物理上是列存储，同一行内可以包含多个列族，列族可以理解为把多个列进行归类，同一个列族内的列字段物理存储在一块区域。

HBase 中数据以 Region 的形式来管理

Region 可以理解成，水平上的数据分块，以 rowKey 进行数据切分，范围 [rowKey1 ~ rowKey2]，[rowKey2 ~ rowKey3] ... 相同区间的数据落在同一个 Region 内。

一个 Region 内部包含一个或者多个 Store，一个 Store 负责这个 Region 下一个列组的列字段存储。每个 Store 由 MemStore 和 StoreFile 组成，数据被写入 Store 时，会优先写入内存 MemStore 中，类似内存数据缓冲区，最终会持久化到 StoreFile，StoreFile 底层是 HFile，存储在 HDFS 上。

可靠性保障：HFile 文件依赖 HDFS 文件系统的可靠性，每个 HFile 默认三个副本；WAL 机制：数据在写入前会先写入HLog，写入 HLog 成功后才会进行真正的数据读写，HLog 用于数据容错恢复，和 binLog 类似

{% asset_img region.jpg 存储结构 %}

##### 1.2 数据格式

以下三条记录为例：

|rowKey|name(列组A)|age(列组A)|addr(列组B)|
| ---- | ---- | ----| ---- |
|   1   |   Tom    |12|US|
|   2   |   Jerry   |15|NULL（空值）|
|   3   |   Mary   |18|NULL（空值）|

假设上述三条数据的 rowKey[1~3] 落在同一个 Region 内，则数据在 Region 内的数据分布如下：

{% asset_img regionData.jpg 数据分布 %}

空列不占存储空间




##### 行列存储对比
###### 行存

* 优点
  * 行存储一行数据存储在一起，读取时可以很方便的查询邻近的字段信息。方便修改一行的记录。传统存储，更多使用的是查询、展示的场景，在这中使用场景下比较适合行存储，
* 缺点
  * 但是如果行查询只需要某些字段，整行的记录仍然需要被读取

###### 列存

* 优点
  * 在大数据分析场景，很多数据都是append-only，不需要对数据进行修改；同时数据的处理更多的是进行聚合、排序、分组的操作，其中只依赖某些列字段，列存储优势在于高效读取需要的列字段数据，不需要整行读取，在这种情况下行存需要全表扫描。
  * 更新：大数据分析中，往往需要聚合很多业务多维度的数据，这些数据往往是独立的，如果要作宽表数据的聚合操作，比如商品价格和商品库存，由多个业务方来维护时，数据相对独立。如果使用行存储的话，部分的数据更新需要整行记录，涉及到另一部分不相关的数据
  * 列扩展性强，物理上只会存储存在的列字段，利于存储稀疏数据，列字段可以任意水平扩展。行存无法动态扩展
  * 相同数据类型列字段存储在一起，可以更好地进行数据压缩
* 缺点
  * 如果需要列字段过多的话，查询需要分别查出每个列字段，然后组装成一行返回



#### 数据操作

##### 流程

上面提到，Client 直接和 Region Server 进行数据交互读写操作，和 Master 无关，Master 只负责 Region Server 的管理。客户端 Client 要将记录写入 HBase 或者读取数据，需要根据 table 表名和 rowKey 定位到某个 Region，然后和对应的 Region Server 建立链接进行通信。数据交互流程：

1. Client 首先和 Zookeeper 进行通信，获取 hbase:meta 表的地址，hbase:meta 表本质上也是一张 hbase 表，请求 ZK 是为了获取对应 Region Server 地址
2. Client 访问 hbase:meta 表所在的 Region Server，获取 hbase:meta 数据，根据 table 表名和 rowKey 定位 Region，获取数据所在的 Region Server 地址
3. Client 获取到数据所在的 Region Server 地址，和数据 Region Server 建立链接，进行数据读写操作



##### hbase:meta 表格式

hbase:meta 作为一张 HBase 表，存在某个 Region Server 上，主要有以下字段

|rowKey|regioninfo|seqnumDuringOpen|server|serverstartcode|
|----|----|----|----|----|
| ...    |...|...|...|...|

* rowKey：由 [table, region_start_key, region_id, encodeValue] 构成

  * region_start_key: 这个 Region rowKey 区间的第一个 key，如果为空则是第一个 Region
  * region_id: 该 Region 的 id，通常为创建时的时间戳
  * encodeValue 值，由 table + region_start_key + timestamp 的 MD5 产生，HBase 在 HDFS 上实际存储 Region 的路径使用的是此 MD5 值，例：

  ```
  test,,1611232663206.6956c3fdf27be740b3fd2bf8a2b07fd
  ```

* regioninfo: 该 Region 相关 info 信息，例：

  ```
  {ENCODED => 6956c3fdf27be740b3fd2bf8a2b07fdb, NAME => 'test,,1611232663206.6956c3fdf27be740b3fd2bf8a2b07fdb.', STARTKEY => '', ENDKEY => ''}
  ```

* seqnumDuringOpen: Region 序列号

* server: 该 Region 所在 Region Server 的地址

* serverstartcode：该 Region 所在 Region Server 的启动时间



{% asset_img meta.jpg meta 示例 %}




#### 参考

* https://developer.ibm.com/zh/articles/ba-cn-bigdata-hbase/