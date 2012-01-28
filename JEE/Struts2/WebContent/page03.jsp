<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Page03 : File upload</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<s:actionerror />
	<s:actionmessage />
    <s:form action="action03" method="POST" enctype="multipart/form-data">
        <s:file name="uploadedFile" label="File"/>
        <s:submit />
		<!-- Put a generated token see sample of page05 -->
		<s:token name="token"/>		        
    </s:form>
File informations : <s:property value="fileInfos" />


</body>
</html>