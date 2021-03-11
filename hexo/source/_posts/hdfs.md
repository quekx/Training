---
title: 学习笔记(11):hdfs
date: 2021-03-11 14:55:02
tags:
---



### HDFS 写流程

{% asset_img hdfs-write.png hdfs write %}

<!-- more -->

写步骤：

1. HDFS client 调用 DistributedFileSystem 的 create 方法，创建 FSDataOutputStream 文件输出流对象
2. 通过 RPC 与 NameNode 进行通信，NameNode 在 HDFS Namespace 中创建一个文件条目，用于记录文件的 block 信息，初始为空；然后 NameNode 会返回文件每个数据块 block 需要拷贝的 DataNode 地址。这些步骤封装在 FSDataOutputStream 内部
3. 获取数据 block 需要拷贝的 DataNode 地址后，client 从 NameNode 返回的 DataNode 列表中，获取最近的第一个 DataNode 节点，将 DataNode 列表和数据发送至此节点中
4. 第一个 DataNode 节点接受到 packet 数据，先存储下来，然后向第二个 DataNode 发送；第二个 DataNode 操作以此类推
5. 每个 DataNode 节点完成数据拷贝后，向前一个节点发送 ACK 确认
6. 最后 client 收到写入完成的 ACK 通知，调用 FSDataOutputStream 的 close 方法，关闭文件流写入
7. client 最后调用 DistributedFileSystem 对象的 complete 方法，通知 NameNode 文件写入成功，NameNode 会将相关结果记录到 editlog 中

文件数据写完后，每一份 block 数据会在多个 DataNode 上存在多个副本

### HDFS 读流程

{% asset_img hdfs-read.png hdfs read %}

读步骤
1. HDFS client 调用 DistributedFileSystem 的 open 方法，创建 FSDataInputStream 文件输入流对象
2. 通过 RPC 与 NameNode 进行通信，从NameNode 获取文件每个数据块 block 以及数据块所在的 DataNode 地址
3. client 与 DataNode 信息通信，读取 block 数据
4. client 读完所有的 block 后，合并成一个大文件
5. 关闭文件流读取