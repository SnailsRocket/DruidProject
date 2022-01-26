# Redis
## 简介
Redis 是一个高性能的Key-Value类型的非关系型数据库
### Redis单线程问题
Redis的单线程指的是网络请求模块使用了一个线程(所以不考虑并发安全)，即一个线程处理所有网络请求，其他模块仍使用多个线程。
Redis采用多路复用

## 常见的数据类型
### String


### List


### Hash


### Set


### SortedSet/ZSet


## 分布式锁

## 持久化机制

