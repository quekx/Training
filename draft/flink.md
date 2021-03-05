## JobManager HA 高可用保障

```
high-availability.cluster-id=application_1612439397310_35199
high-availability.zookeeper.client.session-timeout=120000
high-availability.storageDir=hdfs://na61storage/tmp/blink3.0/ha
high-availability.zookeeper.quorum=mse-12166e44-zk.mse.aliyuncs.com:2181
high-availability=zookeeper
```



ZooKeeper 是一个独立的服务，通过选举提供高可用的分布式协调功能和轻量级的持久化状态存储。

meta 信息会存在 hdfs 某个目录中，JobManager 挂掉后，ZooKeeper 会







