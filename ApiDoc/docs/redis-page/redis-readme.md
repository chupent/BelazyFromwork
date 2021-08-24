# Redis

**Redis是一个key-value类型的内存数据库，整个数据库加载在内存中操作，定期通过异步操作将数据flush到磁盘进行保存。**

- 10w/s读写操作
- key、value最大限制512M(建议不超过1kb)

**缺点：受物理内存影响，不可用于海量数据高性能读写，因此主要局限小数据量高性能操作和运算。**



#### Redis支持数据类型

- String(字符串)

- List（集合）

- Set（集合）

- Sorted Set（有序集合）

- Hash（哈希）

	

#### 数据淘汰策略

1. no-eviction:禁止驱逐数据
2. allkeys-lru:从数据集（server.db[i].dict）中挑选最近最少使用的数据淘汰
3. allkeys-random:从数据集（server.db[i].dict）中任意选择数据淘汰
4. volatile-lru:从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰
5. volatile-random:从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰
6. volatile-ttl:从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰

#####  淘汰机制：

- ​	LRU淘汰机制

- ​	TTL淘汰机制



#### 应用场景

1. 会话缓存
2. 全页缓存
3. 队列
4. 排行榜、计数器
5. 发布/订阅









