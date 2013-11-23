**********
* README *
**********

This project explore the "Contexts and Dependency Injection" (named "CDI") feature of the Java EE 6 Platform.

This project:
- Study use of CDI in a light web context using Tomcat 7 as web container,
- Study use of CDI in unit testing context, for this point the library "CDI-Unit" was used instead of "JBoss Arquillian"
because it's more lightweight and straightforward.

This project is Maven based, then find below command to run project:
=> "mvn clean tomcat7:run-war"
Start web application, use url "http://localhost:9453/jee6cdi/test" to execute the servlet sample.
Show use of CDI in a web context (servlet).

=> "mvn clean test"
Execute unit test.
Show use CDI in unit testing context.


Links (also included into sources code files at keypoints):
CDI Oracle documentation
http://docs.oracle.com/javaee/6/tutorial/doc/gjbnr.html

CDI-Unit user guide
http://jglue.org/cdi-unit-user-guide

JBoss Weld documentation (CDI RI)
http://docs.jboss.org/weld/reference/latest/en-US/html/

----------------------
Dominique Righetto
December 2013