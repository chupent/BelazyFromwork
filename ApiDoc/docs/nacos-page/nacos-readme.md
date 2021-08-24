# Nacos
Nacos 致力于帮助您发现、配置和管理微服务。Nacos 提供了一组简单易用的特性集，帮助您快速实现动态服务发现、服务配置、服务元数据及流量管理。
Nacos 帮助您更敏捷和容易地构建、交付和管理微服务平台。 Nacos 是构建以“服务”为中心的现代应用架构 (例如微服务范式、云原生范式) 的服务基础设施

**官方网站:https://nacos.io/zh-cn/index.html** 


### Nacos启动 & 关闭
**服务启动命令(standalone代表着单机模式运行，非集群模式)**
`sh startup.sh -m standalone`
**服务关闭命令**
`sh shutdown.sh`

**访问:http://localhost:8848/nacos**

查看端口占用:
查全部:netstat -nultp
查看指定端口:netstat -anp |grep 8080