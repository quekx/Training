#### 事务

##### 死锁方法
* 检测死锁循环依赖，立即返回错误
* 等待锁超时

InnoDB处理死锁：将持有行级排它锁最少的事务回滚




##### 事务日志

* 使用事务日志，存储引擎可以只修改内存，然后把变更日志写入磁盘，不必把数据写入磁盘
* 日志为顺序IO
* WAL机制 -> Write-Ahead Logging



#### MVCC 

多版本并发控制
可以认为MVCC是行级锁的一个变种，在很多情况下避免加锁操作


MVCC是通过保存数据在某个时间点的快照实现的
* 不管执行多长时间，每个事务看到的数据是一样的
* 根据不同事务开始时间不同，每个事务对同一张表、同一个时间看到的数据可能不同



MVCC实现方式

1. 乐观并发控制
2. 悲观并发控制



InnoDB实现
通过在每行记录后面保存两个隐藏列实现，两个列分别保存

1. 行的创建时间
2. 行的过期时间

实际保存值为系统版本号
每开启一个事务，系统版本号会递增
事务开始的系统版本号，会作为事务的版本号，作为查询数据的依据



实现场景

* SELECT
  * InnoDB只查询创建版本号小于等于当前事务版本的行
    * 这样保证该行是之前事务或者是当前事务创建的
  * InnoDB只查询删除版本号为空或者大于当前事务版本的行
    * 这样保存该行在事务开始前，没有被删除
* INSERT
  * InnoDB正常插入新记录，设置记录的创建时间为当前事务版本
* DELETE
  * InnoDB删除为伪删除，设置记录的删除时间为当前事务版本
    * 伪删除，这样保证当前事务之前的事务能正常读到值
* UPDATE
  * InnoDB更新为伪删除，新插入一行新纪录，同时标记原来的记录为删除



#### 表信息

显示表信息

```
mysql> show table status like 'sp_flow' \G;
*************************** 1. row ***************************
           Name: sp_flow
         Engine: InnoDB
        Version: 10
     Row_format: Dynamic
           Rows: 4
 Avg_row_length: 12288
    Data_length: 49152
Max_data_length: 0
   Index_length: 0
      Data_free: 0
 Auto_increment: NULL
    Create_time: 2021-12-30 11:01:12
    Update_time: 2022-01-04 12:02:42
     Check_time: NULL
      Collation: utf8mb4_0900_ai_ci
       Checksum: NULL
 Create_options:
        Comment: 爬虫任务表
1 row in set (0.01 sec)
```

| 字段            | 含义                           |
| --------------- | ------------------------------ |
| Name            | 表名                           |
| Engine          | 引擎                           |
| Row_format      | 行的格式                       |
| Rows            | 表的行数                       |
| Avg_row_length  | 平均每行字节数                 |
| Data_length     | 表数据大小字节数               |
| Max_data_length | 表数据最大容量，该值和引擎相关 |
| Index_length    | 索引大小字节数                 |
| Auto_increment  | 下一个自增Auto_increment的值   |
| Collation       | 字符集和字符列排序规则         |



#### InnoDB概览

* 使用MVCC支持高并发
* 四个标准隔离级别，默认：可重复读
* 通过间隙锁防止幻读，锁定索引中的间隙，防止幻影行插入











