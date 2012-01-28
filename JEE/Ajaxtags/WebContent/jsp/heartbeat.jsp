<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<head>
  <title>Heart beat Sample</title>
  <script type="text/javascript" src="${contextPath}/js/prototype-1.4.0.js"></script>
  <script type="text/javascript" src="../js/scriptaculous.js"></script>
  <script type="text/javascript" src="../js/overlibmws.js"></script>
  <script type="text/javascript" src="${contextPath}/js/ajaxtags.js"></script> 
  <link type="text/css" rel="stylesheet" href="../css/ajax.css" />
</head>
<body>

<div id="portletArea">
  <ajax:portlet
    source="testPortlet"
    baseUrl="${contextPath}/SampleGenerator"
    classNamePrefix="portlet"
    title="Heart Beat Portlet"
    imageClose="${contextPath}/img/close.png"
    imageMaximize="${contextPath}/img/maximize.png"
    imageMinimize="${contextPath}/img/minimize.png"
    imageRefresh="${contextPath}/img/refresh.png"
    refreshPeriod="10" />
</div>  
</body> 
</html>
