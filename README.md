# Learn Java Spring Boot Framework

This is an example app to learn how to build Java Web application.

I am taking course "Master Spring Boot 3 & Spring Framework 6 with Java" by in28Minutes from Udemy.

## Local Development

### MySQL start

```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:8-oracle
```

### MySQL commands

```
mysqlsh
\connect todos-user@localhost:3306
\sql
use todos
select * from todo;
\quit
```