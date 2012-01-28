**********
* README *
**********

This project provide a web application to access to a server file system (server hosting the web application). 

For example : This application is usefull when you work with a application server and you want to access to your logs files
but you do not have a user to access to the server using FTP,SSH,... protocols.

This project is in the same time a sample of use of Flex (for the front) with BlazeDS (for the backend).

The IDE used is Eclipse Europa 3.3 with the FlexBuilder 3.0 plugin

** Links 
Flex SDK     : http://www.adobe.com/products/flex/?promoid=BPDEQ
Flex Builder : http://www.adobe.com/products/flex/features/flex_builder/
BlazeDS      : http://opensource.adobe.com/wiki/display/blazeds/BlazeDS/

** Deployment on Oracle application server (OC4J)
Start the OC4J Container with the following option "-Doc4j.jmx.security.proxy.off=true" otherwise 
you get a java.lang.SecurityException error "Unauthorized access from application to MBean".
Add also the JVM option "-Xmx512M" in order to access to more large files

-------------------
D. Righetto
December 2008 