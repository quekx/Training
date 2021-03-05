---
title: 学习笔记(9):hadoop shuffle
date: 2021-03-04 17:47:40
tags:
---

> 在 MapReduce 大数据处理中，除了 Map/Reduce 数据计算处理逻辑，剩余的步骤主要为数据的传输即 shuffle 过程，涉及到数据的序列化/反序列化、网络IO、磁盘IO，shuffle 过程至关重要



## hadoop shuffle 流程

{% asset_img hadoop-shuffle.png hadoop shuffle %}

<!-- more -->



整个数据 shuffle 流程分为两个阶段

1. map shuffle，即数据从 Map 端输出的过程
2. reduce shuffle，即数据输入到 Reduce 端的过程



### map shuffle

数据在 Map Task 端处理完毕后，会经过以下流程：

1. Map 首先将数据写入内存中的环形缓冲区，缓冲区数据写满后，批量写出到磁盘上，这样 Map 处理结果会分散在很多小文件中，写出的文件前数据会在内存中进行排序并且分区 partition，不同的 partition 文件对应下游不同的 Reduce Task。如果此时设置了 Combiner，会将内存中的分区排序结果进行 combine 操作后写出。
2. Map 端写出分区小文件完成后，会进行数据文件合并，为了提高传输效率，相同 partition 的文件数据会合并成一个大文件，合并后大文件中的数据也是有序的。



### reduce shuffle

Map 端数据写出完成后，Reduce 端处理流程开始：

1. Reduce 启动复制线程开始复制 Map 写出的合并文件，此时也会先写入内存中，内存满写出到磁盘，形成小文件。如果此时设置了 Combiner，也会在内存对数据进行 combine 之后写出
2. Map 端的数据读取完毕后，Reduce 工作，开始工作之前，Reduce 端也会进行小文件合并，形成一个大文件
3. 文件合并完成后，作为 Reduce 的输入，进行处理

