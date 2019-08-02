# d2givemeprize-backend

social network service with Spring MVC

Frontend Server -> https://github.com/veatoriche/d2givemeprize-frontend


# D2givemePrize

social network service with Spring MVC

Frontend Server -> https://github.com/veatoriche/d2givemeprize-frontend

visit http://d2snsfront.ap-northeast-2.elasticbeanstalk.com/

## 🚪 Introduction

This repository is for example for spring MVC with Oracle Backend for SNS 

## Getting Started

This instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### 🔨 install JDK
intall JDK on http://www.oracle.com (Downloads>JAVA SE>Downloads)
```
JDK 1.8.0_131(64-bit Windows)
```

### 🔨 Install Tomcat
install Tomcat on http://tomcat.apache.org/  
```
Tomcat8 (64-bit Windows.zip)
```

### 🔧 Set JDK version
```
system -> environment variable -> System variabel -> PATH
```
add downloaded jdk and check on cmd by enter 'javac', 'java'
```
JDK documents: https://docs.oracle.com/javase/8/docs/api/index.html
```

### 🔧 Set Server
```
Eclipse tab Window -> show view -> Other -> servers
```
```
Create new Server -> Apache -> Tomcat v8.5
```
```
Browse directory and add downloaded Tomcat 
```
```
run Server
```

### 🖥 Encoding Type 
```
 Window -> Preference -> Encoding -> General>Content Types -> Text -> UTF-8
```

### Database
you have to insert your database infromation on Pom.xml > datasource dependency
```
<!-- oracle datasource -->
	<bean id="oracleDatasource"
		class="oracle.jdbc.pool.OracleDataSource" destroy-method="close">
		<property name="URL" value="..." />
		<property name="user" value="..." />
		<property name="password" value="..." />
		<property name="connectionCachingEnabled" value="true" />
		<qualifier value="main-db" />
	</bean>
```


## License
This project is licensed under the Apache License -> check out the [LICENSE](https://github.com/9oominsoo/d2givemeprize-backend/blob/master/LICENSE)
