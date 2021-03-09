---
title: 学习笔记(10):flink shuffle
date: 2021-03-09 15:51:17
tags:
---

> 与批处理 MapReduce 过程类似，flink 实时流处理中，数据在处理节点中的 shuffle 传输，也是至关重要的过程



## 整体结构

{% asset_img flink-shuffle.png flink shuffle %}

<!-- more -->



## shuffle 流程

在 hadoop 批处理中，节点之间的数据传递是先将数据写入磁盘，通过磁盘作为媒介来进行数据传输

在 flink 流处理中，节点之间的数据传递是通过网络来进行



### 上游算子 shuffle

1. 当一条记录被上游算子处理完毕后，通过 emitRecord() 方法处理提交，数据会被序列化后写入 buffer，直到遇到以下情况后被写出：
   1. buffer 区被写满
   2. buffer 未写满，但是超过时间限制
   3. 遇到特殊 Event，触发 Checkpoint 的 Barrier 数据
2. buffer 首先会被写出到本地 buffer pool 中，然后 buffer pool 的数据会在 result partition 中，被 partition 到不同的 subPartition，每个 subPartition 对应下游算子的一个 subTask 实例，subPartition 中的数据会发送到对应的下游 subTask 中
3. 下游 netty server 端线程会从 subPartition queue 中取走 buffer，通过网络传输至下游



### 下游算子 shuffle

1. 上游 netty client 端接受到从 server 端发送来的数据后，会将 buffer 插入到数据 parition 对应 subTask 的 input channel 中
2. input channel 中的 buffer 数据会被拷贝到本地 buffer pool，然后经过反序列后，作为下游算子的输入



同一个 TaskManager 内的多个 subTask 实例会公用一个 netty 组件进行通信，对同一个 TCP channel 进行复用，这样可以减少大规模下进程之间的网络连接。

但由于复用连接进行数据传输，当某一个 subTask 实例出现了反压时，buffer pool 或者 channel 资源被沾满，其他 subTask 无法接受输入 buffer，运行停止