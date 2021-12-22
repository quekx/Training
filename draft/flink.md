

### JobManager HA 高可用保障
```
high-availability.cluster-id=application_1612439397310_35199
high-availability.zookeeper.client.session-timeout=120000
high-availability.storageDir=hdfs://na61storage/tmp/blink3.0/ha
high-availability.zookeeper.quorum=mse-12166e44-zk.mse.aliyuncs.com:2181
high-availability=zookeeper
```

ZooKeeper 是一个独立的服务，通过选举提供高可用的分布式协调功能和轻量级的持久化状态存储。

主备 JM，flink 任务会启动多个 JobManager 实例，meta 信息会存在 hdfs 某个目录中，当 Leader JobManager 挂掉后，ZooKeeper 会从 Standby JobManager 选举出一个新的 Leader，新的 JM 会从 hdfs 读取 meta 信息，恢复作业运行。



Flink SQL 消息撤回机制



