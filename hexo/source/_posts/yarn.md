---
title: 学习笔记(8):YARN
date: 2021-02-24 19:48:10
tags:
---

> 大数据处理中，会使用到大规模的集群，如何管理庞大的集群资源，由此诞生了 YARN



## YARN 架构

{% asset_img yarn.png yarn 架构 %}



YARN 主要由以下几部分构成

* ResourceManager，RM 负责整个集群的资源管理与调度
  * 与客户端交互，接受应用程序提交、状态查询等
  * 启动和管理各个应用程序 ApplicationMaster；为任务申请第一个 Container 资源来启动 ApplicationMaster，AM 失败时重启
  * 管理 NodeManager，接受 NM 的资源和健康情况，下达资源管理指令
  * 应用程序资源管理和调度，接受来自应用程序 AM 的资源申请，为程序分配资源
* NodeManager，执行节点上的资源管理器
  * 定期向 RM 汇报资源和健康情况
  * 执行 Container，运行应用程序
* ApplicationMaster，为应用程序管理器，负责程序运行期间的生命周期管理，由用户来实现，每个提交到 YARN 上的应用必须包含
  * 被 RM 启动后，AM 负责和 RM 协调资源（用于运行的 Container）
  * 从 RM 得到资源后，将资源分配给内部的任务
  * 运行期间和 RM 进行通信
  * 监控任务内部的运行状态，并进行内部任务管理
* Container，是 YARN 中的资源抽象，封装了节点的运行资源（CPU/内存），用于运行程序。不同的 Container之间资源隔离，使用 CGroup 实现



