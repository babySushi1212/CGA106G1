## Usage

* [Preparement](#preparement)
* [MySQL](#mysql)
    * [Install](#install)
    * [Local Access](#local-access)
    * [Execute .sql file](#execute-sql-file)
* [Redis](#redis)
    * [Install](#install-1)
    * [Access](#access)
* [Java Env](#java-env)
    * [Install openjdk](#install-openjdk)
    * [Install Maven](#install-maven)
* [Run Project](#run-project)
    * [Initial Database](#initial-database)
    * [Initial .properties file](#initial-properties-file)
    * [Start Project](#start-project)
    * [Connect](#connect)
* [ref](#ref)
* [Dev Rules](#dev-rules)

[toc]

## Preparement

* ec2 setting
    * must >= t3 series
    * memory >= 4G
```bash!
sudo apt update
```

## MySQL

### Install

install mysql server/client/dev
```bash!
sudo apt-get install mysql-server
sudo apt install mysql-client
sudo apt install libmysqlclient-dev
```

install `net-tools`, and test mysql lsitinig
```bash!
sudo apt-get install net-tools
sudo netstat -tap | grep mysql
```

如果需要遠端訪問要設定
* comment `#bind-address = 127.0.0.1`
```bash!
sudo vim /etc/mysql/mysql.conf.d/mysqld.cnf
```

### Local Access

```bash!
mysql -u root -p
```
如果安裝時沒有設定密碼，需要到 `sudo vim /etc/mysql/debian.cnf` 查看
![](https://hackmd.io/_uploads/SJ8tYoo9h.png)
* 注意帳密是紅框中的那組不是 root
```bash!
mysql -u debian-sys-maint -p
```

### Execute .sql file

* Frist, gointo cli and create database
* Second run .sql file
```bash!
mysql -u debian-sys-maint -p DATABASE_NAME < .sql
```

## Redis

### Install

```bash!
sudo apt install redis-server
```

config path
```bash!
/etc/redis/redis.conf
```

檢查是否成功
```bash!
sudo systemctl status redis-server
```

start/stop
```bash!
sudo systemctl strat redis-server
sudo systemctl stop redis-server
sudo systemctl restart redis.service
# 開機自動啟動 Redis 資料庫服務
sudo systemctl enable redis.service
# 取消開機自動啟動 Redis 資料庫服務
sudo systemctl disable redis.service
```
> systemctl = 系統指令

### Access

```bash!
redis-cli
ping
set mykey "My value."
get mykey
del mykey
exit
```

## Java Env

### Install openjdk

搜尋 Ubuntu 提供哪些 openjdk + install
```bash!
sudo apt-cache search openjdk
sudo apt-get install openjdk-17-jre
```

test java env
```bash!
java --version
```

### Install Maven

```bash!
sudo apt install maven
```

test maven env

## Run Project

### Initial Database

```bash!
mysql -u debian-sys-maint -p boardgame < src/main/others/boardgame.sql
```

### Initial .properties file

see .proties template

### Start Project

```java!
git clone https://github.com/babySushi1212/CGA106G1.git
cd CGA106G1
mvn spring-boot:run
```

### Connect

current public IP: 43.207.174.232
* not elastic IP, so this IP will change
* new IP will publish at [wiki page](https://github.com/babySushi1212/CGA106G1/wiki)

```
[public IP]:8080/background/login
[public IP]:8080/foreground/login
```

## ref

* [mysql forum](https://andy6804tw.github.io/2019/01/29/ubuntu-mysql-setting/)
* [redis forum](https://officeguide.cc/ubuntu-linux-redis-database-installation-configuration-tutorial-examples/)
* [java forum](https://ithelp.ithome.com.tw/articles/10248237)

## Dev Rules
* [Dev Ruls](doc/Dev-Rules.md)
