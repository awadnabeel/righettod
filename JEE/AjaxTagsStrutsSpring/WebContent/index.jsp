<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>AJAXTAGS STRUTS SPRING</title>
</head>
<body>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<a href="jsp/ajax/multithread.jsp">AJAX MULTITHREAD</a><br />
<a href="jsp/tiles/tilesLayout1.jsp">TILES LAYOUT v1</a><br />
<a href="jsp/tiles/tilesLayout2.jsp">TILES LAYOUT v2</a><br />
<a href="jsp/tiles/tilesLayout3.jsp">TILES LAYOUT v3</a><br />
<a href="${contextPath}/readMail.do">TEST MAILS</a>
</body>
</html>