# zac-springboot-jpa-thymeleaf-bootstrap
Project in progress:  
-User login and registration web app with admin page

[Admin details:](zac-spring-jpa-thymleaf-bootstrap/blob/master/src/main/java/com/zac/spring/configuration/SetupDataLoader.java#L57)  
email: admin@gmail.com;  
password: admin;

[User details:](zac-springboot-jpa-thymeleaf-bootstrap/blob/90daa09263ec034f06c5858f7eb232a1d44fbc33/src/main/java/com/zac/spring/configuration/SetupDataLoader.java#L59)  
email: user1@gmail.com;  
password: user1;

Technology stack:
* Spring Boot
* Spring MVC
* Spring Data JPA
* MySQL
* Spring Security
* Lombok
* Thymeleaf
* Bootstrap 4
* jQuery
* AJAX



## Requirements

* JDK 8

  Java 8 is required, go to [Oracle Java website](http://java.oracle.com) to download it and install into your system. 
 
  Optionally, you can set **JAVA\_HOME** environment variable and add *&lt;JDK installation dir>/bin* in your **PATH** environment variable.

* Apache Maven

  Download the latest Apache Maven from [http://maven.apache.org](http://maven.apache.org), and uncompress it into your local system. 

  Optionally, you can set **M2\_HOME** environment varible, and also do not forget to append *&lt;Maven Installation dir>/bin* your **PATH** environment variable.  

## Set up MySQL
Configure database according to [application.properties](zac-spring-jpa-thymleaf-bootstrap/blob/328496c1ad1c1347f0b03af1504730cb52ffe3a4/src/main/resources/application.properties#L8) file, or update this file with yours properties.

  
## Running the project
The application uses [Spring Boot](http://projects.spring.io/spring-boot/), so it is easy to run. You can start it any of a few ways:
* Run the `main` method from `SpringbootThymeleafJpaApplication `
* Use the Maven Spring Boot plugin: `mvn spring-boot:run`

## Viewing the running application
To view the running application, visit [http://localhost:8080](http://localhost:8080) in your browser

## i18n Internationalization and localization
(1) http://localhost:8080/?lang=zh_cn
(2) http://localhost:8080/?lang=ja
(3) http://localhost:8080/?lang=jp
(4) http://localhost:8080/?lang=en

## Lombok
https://qiita.com/opengl-8080/items/671ffd4bf84fe5e32557

## Plugin
## Unicode
PropertiesEditor
## JBoss Tools
https://qiita.com/rubytomato@github/items/cb7c40cea2ff3225d5e3
https://stackoverflow.com/questions/44640978/spring-tools-suite-eclipse-database-connection-properties-empty
http://download.eclipse.org/releases/kepler/

## goods
https://dev.classmethod.jp/server-side/java/spring-data-jpa_search/

## Spring Cache
https://www.cnblogs.com/cnjavahome/p/8436219.html

#https://qiita.com/ksby/items/7cf5235f6061ef1600e2
# Spring Boot + Thymeleaf + Enum（列挙型） でドロップダウンリストを表示する

#https://www.tianmaying.com/tutorial/spring-boot-docker
# 在应用代码编写完成后，可以直接将其打包并运行：
Maven:
mvn package
java -jar springboot-thymeleaf-jpa-0.0.1-SNAPSHOT.jar

mvn clean package
(OR mvn install)
java -jar target/springboot-thymeleaf-jpa-0.0.1-SNAPSHOT.jar

## TODO 生成jar后报错
The dependencies of some of the beans in the application context form a cycle:
UserService
SecurityConfig
UserDetailsServiceImpl

