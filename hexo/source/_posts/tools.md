---
title: tools
date: 2020-09-04 14:42:01
tags:
---

## 常用工具
### tail
```
tail -300f shopbase.log #倒数300行并进入实时监听文件写入模式
```

### grep
```
grep forest f.txt   #文件查找
grep forest f.txt cpf.txt #多文件查找
grep 'log' /home/admin -r -n #目录下查找所有符合关键字的文件
cat f.txt | grep -i shopbase    
grep 'shopbase' /home/admin -r -n --include *.{vm,java} #指定文件后缀
grep 'shopbase' /home/admin -r -n --exclude *.{vm,java} #反匹配
seq 10 | grep 5 -A 3    #上匹配
seq 10 | grep 5 -B 3    #下匹配
seq 10 | grep 5 -C 3    #上下匹配，平时用这个就妥了
cat f.txt | grep -c 'SHOPBASE'

grep "xxx" -v f.txt #不包含反向查找
```

### awk
```
cat xx | awk -F= '{print $2}' | awk '{print $1}' | awk -F. '{if ($2=="na61") {print $1"."$2} else if($3=="na61") {print $1"."$2"."$3}}' #分隔处理
```

### netstat
```
netstat -nat | awk  '{print $6}' | sort | uniq -c | sort -rn 
#查看当前连接，注意close_wait偏高的情况
```

### jsp
```
jps -mlvV xxx
```
* -m 输出main方案参数
* -l 显示主类全名
* -v jvm参数
* -V 通过flag file传入的jvm参数

### jstack
```
jstack xxx
```
堆栈状态

### idea插件
* key promoter
* maven helper
* Rainbow Brackets

### VM参数
* 类来自哪个文件
```
-XX:+TraceClassLoading
结果形如[Loaded java.lang.invoke.MethodHandleImpl$Lazy from D:\programme\jdk\jdk8U74\jre\lib\rt.jar]
```
* 应用挂了输出dump文件
```
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/admin/logs/java.hprof
```

