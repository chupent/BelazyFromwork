# BeLazy
BelazyFromwork 基于阿里体系构建云服务架构快速开发框架,方便日后使用



架构
---



服务端部署
---

#### 1、部署基础服务
|服务| 服务名         | 版本 | 端口                                                   | 控制台 | 备注 |
|:----| :------------- | ----- | ------------------------------------------------------------ |-----|-----|
|注册中心| [Nacos](./docs/nacos-page/nacos-readme.md)          | 1.1.3  | 8848 | http://localhost:8848/nacos ||
|数据库| [MySQL](./docs/mysql-page/mysql-readme.md)            |  8.0.24 | 3306 |  ||
|队列服务| [RabbitMQ](./docs/rabbitmq-page/rabbitmq-readme.md)         |   | 5672 | http://127.0.0.1:15672/#/ |账号/密码|
|缓存服务| [Redis](./docs/redis-page/redis-readme.md)            | 3.2.12  | 6379 |  |
|链路追踪| [ZipKin](./docs/zipkin-page/zipkin-readme.md)           | 2.23.3  | 9411 | http://127.0.0.1:9411 |非必要|
|搜索引擎服务| Elasticsearch | 6.8.17 | 9200 | http://127.0.0.1:9200 |非必要(待部署)|
|日志分析平台| Kibana | 6.8.13 | 5601	 | http://localhost:5601 |非必要(待部署)|
#### 2、部署应用服务
|服务| 服务名         | 端口  | 文档                                                   | 简介 |
|:----| :---------- | ----- | ------------------------------------------------------------ |-----|
|监控服务| belazy-admin | 8080  | -- |  |
|网关服务| belazy-gateway | 8000  | [网关服务文档](./docs/belazy-page/api-gateway.md) | 网关统一外网入口、接口鉴权 |
|认证服务| belazy-auth    | 8010  | [认证服务文档](./docs/belazy-page/api-auth.md) | 登录认证 |
|公共基础服务| bbs-common     | 8020  | [公共服务文档](./docs/belazy-page/api-common.md) | 基础数据字典、系统参数配置、菜单、角色、用户....... |
|定时任务服务| bbs-schedul | 8030  |                                                      | 待开发 |
| 工作流程服务 | bbs-workflow         | 8040 |                                                              | 待开发 |
| 外部连接服务 | bbs-**external**-connect | 8050 | | 待开发 |
| 测试服务     | bbs-test             | 9010 | | |
|              |                      |      | | |


前端部署
---

【待开发】





## 门户入口

###### IPhone门户
###### 安卓门户
###### 小程序门户
###### 公众号门户
###### web门户
###### admin管理平台


## 安卓门户

####技术栈 MVVM

* utilcodex 
* SmartRefreshLayout 
* EventBus
* okhttp3
* retrofit


# 完成
1.  Nacos服务注册
2.  Nacos配置管理
3.  日志简单输出
4.  Spring-boot-admin对应用信息进行可视化
5.  Gateway集成
6.  Gateway --熔断机制
7.  Gateway --结合Nacos配置中心完成动态路由
8.  Gateway --全局异常处理
9.  swagger3集成 + knife4j文档增强
10. Gateway实现聚合swagger3+knife4j文档
11. 简单实现Spring OAuth2 + JWT 认证服务
12. Spring OAuth2 + JWT 认证服务 + Redis
13. 自定义Spring OAuth2 + JWT 认证服务 + Redis (手机号/短信验证码)
14. JWT增强、数据持久化mysql数据库
15. Gateway简单鉴权
16. Gateway全局日志输出
17. 认证数据从DB中获取
18. Gateway自定义负载
19. ZipKin实现链路追踪
20. 代码自动生成器开发

#计划完成

nacos网关路由配置修改后，网关服务未监听到变化

11. Gateway实现限流
14. Sentinel之限流
15. Sentinel之熔断
###网关
1. Gateway之路由、限流
    * 路由访问
        2.1 基础URI路由配置方式
        2.2 基于代码的路由配置方式
        2.3 和注册中心相结合的路由配置方式

    * 访问限流
    * 熔断机制 




