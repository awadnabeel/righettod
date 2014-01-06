**********
* README *
**********

** Description

This project contains some examples of implementation for the concepts described into the 
OWASP wiki article "https://www.owasp.org/index.php/Detect_profiling_phase_into_web_application" (file "TheFishingAttackers.docx").

** Prerequisite

Maven 3.x:
http://maven.apache.org/

JavaSE JDK 1.7.x:
http://www.oracle.com/technetwork/java/javase/downloads/index.html

** Run project

Using a shell, run command "mvn clean tomcat7:run-war" and the web application will start 
on url "http://localhost:9344/profdec".

To test "passive" profiling detector, using a browser, call the urls below in sequence:
1. http://localhost:9344/profdec/create
2. http://localhost:9344/profdec/read
3. http://localhost:9344/profdec/update
4. http://localhost:9344/profdec/delete
5. http://localhost:9344/profdec/delete

On last url call, you will be blocked for 1 hour and a message will indicate it into the shell console.


To test "active" profiling detector, using a browser, follow the steps below:
1. call the url "http://localhost:9344/profdec/compute"
2. Using a extension like "Tamper Data" for Firefox, call another time the 
   url "http://localhost:9344/profdec/compute" but, this time, alter the request 
   by changing the cookie "verbose_mode value from "false" to "true".

On last url call, you will be blocked for 1 hour and a message will indicate it into the shell console.

** License

GNU GPL v3 :o)

** Support / remarks

You can contact me on dominique.righetto@gmail.com
