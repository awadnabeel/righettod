**********
* README *
**********

This project explore the "WEBSOCKET" feature of the Java EE 7 Platform.

Links (also included into sources code files at keypoints):
WEBSOCKET API Oracle documentation
http://docs.oracle.com/javaee/7/tutorial/doc/websocket.htm#GKJIQ5

Project was made using NetBeans 8.0 and deployed under Tomcat 8.0.3.0 (port 8084).

Some details about Web Socket behavior with "Sec-WebSocket-Key" and "Sec-WebSocket-Accept" headers during handshake 
(extract from "http://tools.ietf.org/html/rfc6455#page-5"):

Client request:
    GET /chat HTTP/1.1
    Host: server.example.com
    Upgrade: websocket
    Connection: Upgrade
    Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==
    Origin: http://example.com
    Sec-WebSocket-Version: 13

Server response:
    HTTP/1.1 101 Switching Protocols
    Upgrade: websocket
    Connection: Upgrade
    Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=

The server would concatenate the string "258EAFA5-E914-47DA-95CA-C5AB0DC85B11" to form the string "dGhlIHNhbXBsZSBub25jZQ==258EAFA5-E914-47DA-95CA-C5AB0DC85B11".
The server would then take the SHA-1 hash of this,giving the value 0xb3 0x7a 0x4f 0x2c 0xc0 0x62 0x4f 0x16 0x90 0xf60x46 0x06 0xcf 0x38 0x59 0x45 0xb2 0xbe 0xc4 0xea.
This value is then base64-encoded (see Section 4 of [RFC4648]), to give the value "s3pPLMBiTxaQ9kYGzzhZRbK+xOo=".  
This value would then be echoed in the |Sec-WebSocket-Accept| header field.

==> Glassfih 4 & Tomcat 8.0.3.0 follows this rule (tested).

Tomcat user configuration in order to use the secured example ("/secured/*" url):
<?xml version='1.0' encoding='utf-8'?>
<tomcat-users>
  <role rolename="tomcat_role"/>
  <user username="tomcat" password="tomcat" roles="tomcat_role"/>
</tomcat-users>


----------------------
Dominique Righetto
May 2014