<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Page05 : Prevention of duplicate form submission using Token</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<a href="http://struts.apache.org/2.x/docs/token-interceptor.html" target="_blank">More informations</a>
<br /><br />
To view the token check, modify the HTTP request and delete the "token" parameter. <br />
Under Firefox you can use the <a href="https://addons.mozilla.org/en-US/firefox/addon/966">Tamper Data</a> extension.
<br />
<s:actionerror />
<h4>Send a message</h4>
<s:form action="action05">
	<s:textfield label="Message" name="message" required="true"  value="MyBaseMessage" size="50"/>
	<s:submit value="Send message"/>
	<!-- Put a generated token -->
	<s:token name="token"/>	
</s:form>
Your message is :
<s:property value="message" />

</body>
</html>