***********
* README *
***********

This project have to goal to show the integration between JPA (Java Persistance API) and Spring 2.5.x with 
the transaction managed by Spring (using AOP). The JPA implementation used is 
Oracle Toplink Essentials version "2.0-b41-beta2 (03/30/2007)" with the database 
"Oracle Database 10g Express Edition".

In the "sql" directory there available :
- The database scripts (data + tables)
- The database structure view through the file "database.jpg"

To run the "com.drighetto.springjpa.Main" class add the JVM option "-javaagent:${project_loc}/lib/spring-agent.jar"
(cf. file "RunMainJVMOption.jpg")

Note : For the moment JPA with Spring only support ISOLATION_DEFAULT for the transaction isolation, this value is checked
       in the the method "beginTransaction" of the class "org.springframework.orm.jpa.DefaultJpaDialect" and
       the class "org.springframework.orm.jpa.vendor.TopLinkJpaDialect" is a sub class of DefaultJpaDialect !


-----------------------
D. Righetto
April 2008