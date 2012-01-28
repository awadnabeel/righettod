<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Page04 : Server side access to context, application, session, request objects</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
    <a href="http://struts.apache.org/2.x/docs/accessing-application-session-request-objects.html" target="_blank">More informations</a>      
    <h1>Iterate on server side maps</h1>
    <s:form action="action04" >
        <s:submit value="Get objects data from the server"/>
		<!-- Put a generated token see sample of page05 -->
		<s:token name="token"/>		        
    </s:form>
	<h4>Context map content</h4>
	<s:iterator status="stat" value="contextAttr" >
		Key : [<s:property value="key"/>] - Value : [<s:property value="value"/>]
		<br/>
	</s:iterator>
	<h4>Application map content</h4>
	<s:iterator status="stat" value="applicationScopeAttr" >
		Key : [<s:property value="key"/>] - Value : [<s:property value="value"/>]
		<br/>
	</s:iterator>	
	<h4>Session map content</h4>
	<s:iterator status="stat" value="sessionScopeAttr" >
		Key : [<s:property value="key"/>] - Value : [<s:property value="value"/>]
		<br/>
	</s:iterator>	
	<h4>Request map content</h4>
	<s:iterator status="stat" value="requestScopeAttr" >
		Key : [<s:property value="key"/>] - Value : [<s:property value="value"/>]
		<br/>
	</s:iterator>	
	<br />
	<br />
	<br />
	<h1>Direct access from a view</h1>
	<h4>Context map content</h4>
	<s:iterator status="stat" value="#attr" >
		Key : [<s:property value="key"/>] - Value : [<s:property value="value"/>]
		<br/>
	</s:iterator>
	<h4>Application map content</h4>
	<s:iterator status="stat" value="#application" >
		Key : [<s:property value="key"/>] - Value : [<s:property value="value"/>]
		<br/>
	</s:iterator>	
	<h4>Session map content</h4>
	<s:iterator status="stat" value="#session" >
		Key : [<s:property value="key"/>] - Value : [<s:property value="value"/>]
		<br/>
	</s:iterator>	
	<h4>Request map content</h4>
	<s:iterator status="stat" value="#request" >
		Key : [<s:property value="key"/>] - Value : [<s:property value="value"/>]
		<br/>
	</s:iterator>

</body>
</html>