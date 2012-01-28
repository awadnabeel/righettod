<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Mail Displayer</title>
</head>
<body>
<logic:present name="MAILS" scope="session">
   <logic:iterate id="msg" name="MAILS" scope="session">
     ORDER : <bean:write name="msg" property="messageNumber"/>
     <br />   
     FROM : <bean:write name="msg" property="from"/>
     <br />
     TO : <bean:write name="msg" property="recipients"/>
     <br />
     SUBJECT: <bean:write name="msg" property="subject"/>
     <br />   
     RECEIVED DATE: <bean:write name="msg" property="receivedDate"/>
     <br />       
     SENT DATE: <bean:write name="msg" property="sentDate"/>
     <br />  
     SIZE : <bean:write name="msg" property="size"/>
     <br /> 
     CONTENT TYPE : <bean:write name="msg" property="contentType"/>
     <br /> 
     CONTENT : <bean:write name="msg" property="content"/>
     <br /> 
     <br />
     <hr />
     <br /> 
     <br />                          
   </logic:iterate>
</logic:present>
</body>
</html>