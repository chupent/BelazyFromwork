# Linux安装Nginx

### Yum安装方式

1、执行安卓命令yum install  nginx

2、执行启动nginx命令：systemctl start nginx.service

3、测试nginx访问：浏览器访问地址

4、设置开机启动：systemctl enable nginx.service

5、配置信息

	5.1 网站文件存放默认目录：/usr/share/nginx/html
	
	5.2 网站默认站点配置：/etc/nginx/conf.d/default.conf
	
	5.3 自定义Nginx站点配置文件存放目录: /etc/nginx/conf.d/

   5.4 Nginx全局配置：/etc/nginx/nginx.conf

6、配置文件方式启动：nginx -c nginx.conf

7、配置防护墙：

8、重启防火墙：firewall-cmd --reload

9、查看公网IP：

`ip addr show eth0 | grep inet | awk '{ print $2; }' | sed 's/\/.*$//'`

### 离线安装方式

##### 安装所需插件

##### 1、安装gcc

gcc是linux下的编译器，支持可以编译 C,C++,Ada,Object C和Java等语言

命令查看gcc版本: gcc -v

安装命令：yum -y install gcc

##### 2、pcre、pcre-devel安装

pcre是一个perl库，包括perl兼容的正则表达式库，nginx的http模块使用pcre来解析正则表达式，所以需要安装pcre库。

安装命令：yum install -y pcre pcre-devel

##### 3、zlib安装

zlib库提供了很多种压缩和解压缩方式nginx使用zlib对http包的内容进行gzip，所以需要安装

安装命令：yum install -y zlib zlib-devel

##### 4、安装openssl

安装命令：yum install -y openssl openssl-devel

##### 安装nginx

下载包：wget   http://nginx.org/download/nginx-1.9.9.tar.gz 

解压包：tar -zxvf  nginx-1.9.9.tar.gz

切换到解压后nginx-1.9.9的目录，执行两个命令

```
./configure --prefix=/usr/local/nginx

make&&make install
```

启动服务：/usr/local/nginx/sbin/nginx

