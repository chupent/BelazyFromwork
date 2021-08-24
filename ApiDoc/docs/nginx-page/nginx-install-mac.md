# Mac安装Nginx

#### Homebrew安装方式

#### 准备工作

检查Homebrew是否安装，如果安装跳过此步骤。以下为homebrew安装完毕。

```bash
chupengtang@MacBook-Pro-2 ~ % brew --version 
Homebrew 2.4.14
Homebrew/homebrew-core (git revision 097f65; last commit 2020-08-30)
Homebrew/homebrew-cask (git revision 185bd; last commit 2020-08-31)
```

#### 安装步骤

1. 执行查找nginx命令 brew search nginx
2. 查看下nginx信息命令（可省略）:brew info nginx
3. 执行安装nginx命令:brew install nginx

#### Nginx for Mac 启动、重启、关闭

1. 检查配置是否正确:/usr/local/Cellar/nginx/1.15.3/bin/nginx  -t -c /usr/local/etc/nginx/nginx.conf

2. nginx服务启动命令:/usr/local/Cellar/nginx/1.15.3/bin/nginx

3. nginx服务重启命令:/usr/local/Cellar/nginx/1.15.3/bin/nginx -s reload

4. 浏览器输入http://localhost：8080后提示“Welcome to nginx”安装正常成功。

5. nginx服务关闭命令:/usr/local/Cellar/nginx/1.15.3/bin/nginx -s stop

	