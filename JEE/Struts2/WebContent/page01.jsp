<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Page01 : Action invocation</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<s:fielderror />
<h4>With validation process actived</h4>
<s:form action="action01a">
	<s:textfield label="Message" name="message" required="true"  />
	<s:submit />
	<!-- Put a generated token see sample of page05 -->
	<s:token name="token"/>		
</s:form>
<h4>With validation process deactived</h4>
<s:form action="action01b" >
	<s:textfield label="Message" name="message" required="true" />
	<s:submit />
	<!-- Put a generated token see sample of page05 -->
	<s:token name="token"/>			
</s:form>
<br />
Your message is :
<s:property value="message" />


</body>
</html>