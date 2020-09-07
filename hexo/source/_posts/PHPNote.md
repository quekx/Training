---
title: 学习笔记(5):PHP
date: 2017-07-10 23:52:38
categories: 
- tips
tags:
- PHP
toc: true
---

时间函数：

```
$date = date('Ymd'); // 'Y-m-d H:m:s'
$first = 1; //$first = 1 表示每周星期一为开始日期 0表示每周日为开始日期
$w = date('w', strtotime($date));  //获取当前周的第几天 周日是0,周一到周六是1-6
$now_start = date('Ymd', strtotime("$date -" . ($w ? $w - $first : 6) . ' days')); // 这周周一
$last_start = date('Ymd', strtotime("$now_start - 7 days")); // 上周周一
return $last_start;
```
<!-- more -->

时间格式转换：

```
$date = date('Ymd');      // Ymd格式
$time = strtotime($date); // 转换为时间戳
$date = date('Ymd H:m:s'); // Ymd H:m:s格式
```

curl发送Get

```
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);   //设置url
curl_setopt($ch, CURLOPT_HEADER, 0);   // 不输出头部
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); // 返回结果作为string
$response = curl_exec($ch);
curl_close($ch);
```

Json解析

```
$result = json_decode("" . $response, true);  // true解析为数组
$data = $result["data"];

```
文件写入

```
$fileName = "puvLog.txt";
$time = date("Y-m-d H:i:s", time());
$log = $time . "  " . $log . "\n";
file_put_contents($fileName, $log, FILE_APPEND); // 尾部追加
```
