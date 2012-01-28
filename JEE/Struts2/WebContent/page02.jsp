<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Page02 : Internationalization</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<a href="http://struts.apache.org/2.x/docs/localization.html" target="_blank">More informations, link 1</a>
<br />
<a href="http://struts.apache.org/2.0.14/docs/how-do-i-set-a-global-resource-bundle.html" target="_blank">More informations, link 2</a>
<br />
<br />
<s:form action="action02" >
	<s:textfield key="page02.textfield.label" name="message" required="true" />
	<s:submit />
	<!-- Put a generated token see sample of page05 -->
	<s:token name="token"/>			
</s:form>
<br />
<s:text name="page02.display.message" />&nbsp;<s:property value="message" />


</body>
</html>