---
title: 学习笔记(8):YARN
date: 2021-02-24 19:48:10
tags:
---

> 大数据处理中，会使用到大规模的集群，如何管理庞大的集群资源，由此诞生了 hadoop YARN



## YARN 架构

{% asset_img yarn.jpg yarn 架构 %}

<!-- more -->



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
* Container，是 YARN 中的资源抽象，封装了节点的运行资源（CPU/内存），用于运行程序。不同的 Container 之间资源隔离，使用 CGroup 实现



## YARN 组件通信

{% asset_img communication.jpg 组件间通信 %}



各个组件之间的通信，由客户端主要轮询发起



## YARN 工作流程

{% asset_img step.jpg 工作流程 %}



1. 用户使用客户端向 ResourceManager 提交任务，包含 ApplicationMaster 程序，ApplicationMaster 启动命令，实际的任务应用 Task
2. RM 为此应用程序分配第一个 Container，用来启动 AM。RM 与对应的 NM 进行通信，要求启动 AM
3. AM 启动后，向 RM 注册和汇报。这样用户可以通过 RM 来查看任务状态
4. AM 向 RM 轮询申请资源，用于运行内部任务
5. AM 申请到 Container 后，与对应的 NodeManager 进行通讯，要求启动任务
6. NM 为每个任务准备好运行环境（环境变量、JAR 包等），将任务命令写到一个脚本中，运行脚本启动任务
7. Task 任务启动后，向 AM 注册和汇报，AM 可以掌握各个任务的运行状态和处理
8. 当 AM 检测到 Task 都运行结束后，向 RM 注销并关闭自己



