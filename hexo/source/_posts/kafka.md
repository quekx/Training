---
title: 学习笔记(4):kafka
date: 2017-05-08 23:51:55
categories: 
- framwork
tags: 
- Java
- Kafka
toc: true
---

## Kafka

Kafka是一种分布式的消息订阅发布系统。

### Kafka结构

消息传递模型常见有

* 基于缓存的生产者/消费者模型
* 基于观察者的发布/订阅模型

Kafka结构中实现消息生产者/消费者，使用的就是发布订阅模型，具体结构如下：

<!-- more -->

{% asset_img kafka.png Kafka中消息模型 %}

Kafka中Consumer和Producer之间通过特定topic进行消息传递。

### Kafka文件系统

在Kafka中每个topic分配了单独的文件夹，topic下的每个partition也有其单独的文件夹，partition文件夹下即存放了对应的消息文件，随着Kafka中消息的增加，Kafka会将消息分段存储，每段称为segment。

每个segment由.index文件和.log文件组成。

segment文件命名：文件名为19位数字，初始文件为全零，后续文件为上一个文件中最后一条消息的offset，并用零高位补齐19位。这样此文件的起始offset便为文件名fileName + 1。

.index文件为索引文件，其中存放了消息序号所对应的消息，其在.log数据文件中的偏移位置。

segment文件虽然是持久化的，可以读取其中的历史消息，但其不是永久存在的，有其生命周期，具体生命周期由Kafka服务器的参数配置决定。

