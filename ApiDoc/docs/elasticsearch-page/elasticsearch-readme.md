Elasticsearch
===

linux安装

下载文件

> wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.12.1-linux-x86_64.tar.gz

2、解压文件

> ```
> tar -zxvf elasticsearch-7.8.1-linux-x86_64.tar.gz -C /usr/local/
> ```

3、创建Linux用户，elasticsearch 5以后不支持root启动

> a、创建elasticsearch
>
> [root@iZuf66uk6c1etzbj8uw6t1Z bin]# adduser elasticsearch
>
> b、设置密码（Tang@chupenng）
>
> [root@iZuf66uk6c1etzbj8uw6t1Z bin]#  passwd elasticsearch
>
> c、将对应文件夹授权给用户
>
> [root@iZuf66uk6c1etzbj8uw6t1Z bin]# chown -R elasticsearch /usr/local/elasticsearch-7.12.1
>
> d、切换用户
>
> [elasticsearch@iZuf66uk6c1etzbj8uw6t1Z bin]$ su elasticsearch

4、进入对应目录启动elasticsearch

> [elasticsearch@iZuf66uk6c1etzbj8uw6t1Z bin]$  sh ./elasticsearch

5、测试是否启动成功

> [elasticsearch@iZuf66uk6c1etzbj8uw6t1Z bin]$ curl IP:9200







