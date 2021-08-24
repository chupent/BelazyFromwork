Redis安装
===

### Linux/Mac源码安装

1. 下载redis（官网http://redis.io/）
2. 解压文件：sudo tar -zxf redis-3.2.0.tar 
3. 将解压文件移动到/usr/local/目录,并cd 到目录下
4. sudo make test 测试编译
5. sudo make install  安装
6. 启动Redis

### Linux系统yum安装Redis

1、执行安装redis命令：yum install redis

2、查看Redis安装了哪些文件：find / -name "redis*"

3、启动Redis服务：*service redis start*

4、打开Redis客户端：*redis-cli*

5、允许远程访问redis

​		5.1 编辑redis.config文件：*vim /etc/redis.conf*

​       5.2 将# bind 127.0.0.1 ::1*  注释，新加bind 192.168.146.31，保存文件

​	   5.3 重启服务

### 配置

1、进入在redis目录，下建立bin，etc，db三个目录
2、把/usr/local/redis/src目录下的mkreleasehdr.sh，redis-benchmark， redis-check-rdb， redis-cli， redis-server拷贝到bin目录
3、拷贝redis.conf 到etc下
4、修改redis.conf
5、启动服务：./bin/redis-server etc/redis.conf
6、查看日志：tail -f log-redis.log
7、打开redis客户端客户端： ./bin/redis-cli