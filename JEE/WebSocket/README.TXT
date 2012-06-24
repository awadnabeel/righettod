**********
* README *
**********

This project explores HTML5 WebSocket Client/Server feature using Tomcat 7.0.28 . 

Project use Maven under Eclipse.

To run the project type the command into a shell "mvn clean tomcat7:run-war" and open several
browser tabs (or differents browser) with url "http://localhost:8787/websocket" to see behaviors.

Remarks with the version of Tomcat 7.0.28 (the most recent version when this sample has been implemented):

- There an issue with the WebSocket sub protocols exchange between Client/Server. Sub protocols are exchanged using HTTP 
headers during the connection upgrade phase (from HTTP to WEBSOCKET protocol). Firefox 12 and Chrome 19 send sub protocols 
using HTTP header named "Sec-WebSocket-Protocol" and the Tomcat WebSocket Servlet implementation expect a header named 
"Sec-WebSocket-Protocol-Client" then the sub protocols list received by server side handler is always null.

- The check on request origin use the HTTP request header "Origin" to extract the sender of the upgrade request. if
you alter the header value you can change the sender informations and then bypass origin checking.

----------------------
Dominique Righetto
June 2012