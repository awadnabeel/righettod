<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Servlet 3.0 Exploration</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/Sample01SyncServlet">Synchronous servlet sample statically declared</a>
<br />
<a href="${pageContext.request.contextPath}/Sample02AsyncServlet">Asynchronous servlet sample statically declared</a>
<br />
<a href="${pageContext.request.contextPath}/Sample03SyncServlet">Synchronous servlet sample dynamically declared</a>
<br />
<a href="${pageContext.request.contextPath}/Sample04SyncServlet">Synchronous servlet sample statically declared in web fragment n°01 module</a>
<br />
<a href="${pageContext.request.contextPath}/Sample05SyncServlet">Synchronous servlet sample statically declared in web fragment n°02 module</a>
<br />
<a href="${pageContext.request.contextPath}/Sample06SyncServlet">Synchronous servlet sample statically declared in main app. and configured in main app. and over all web fragment modules</a>
<br />
<a href="${pageContext.request.contextPath}/Sample07SyncServlet">Synchronous servlet sample statically declared in web fragement n°03 module and configured in main app. and over all web fragment modules</a>
</body>
</html>