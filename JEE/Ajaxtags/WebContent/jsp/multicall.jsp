<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<head>
  <title>MultiCall Sample</title>
  <script type="text/javascript" src="${contextPath}/js/prototype-1.4.0.js"></script>
  <script type="text/javascript" src="../js/scriptaculous.js"></script>
  <script type="text/javascript" src="../js/overlibmws.js"></script>
  <script type="text/javascript" src="${contextPath}/js/ajaxtags.js"></script> 
  <link type="text/css" rel="stylesheet" href="../css/ajax.css" />
</head>
<body>

<div id="portletArea1">
  <ajax:portlet
    source="testPortlet1"
    baseUrl="${contextPath}/TimeGenerator"
    parameters="src=testPortlet1"
    classNamePrefix="portlet"
    title="MultiCall Sample 1"
    imageClose="${contextPath}/img/close.png"
    imageMaximize="${contextPath}/img/maximize.png"
    imageMinimize="${contextPath}/img/minimize.png"
    imageRefresh="${contextPath}/img/refresh.png"
    refreshPeriod="10" />
</div>  
<br><br><br>
<div id="portletArea2">
  <ajax:portlet
    source="testPortlet2"
    baseUrl="${contextPath}/TimeGenerator"
    parameters="src=testPortlet2"
    classNamePrefix="portlet"
    title="MultiCall Sample 2"
    imageClose="${contextPath}/img/close.png"
    imageMaximize="${contextPath}/img/maximize.png"
    imageMinimize="${contextPath}/img/minimize.png"
    imageRefresh="${contextPath}/img/refresh.png"
    refreshPeriod="20" />
</div> 
<br><br><br>
<div id="portletArea3">
  <ajax:portlet
    source="testPortlet3"
    baseUrl="${contextPath}/TimeGenerator"
    parameters="src=testPortlet3"
    classNamePrefix="portlet"
    title="MultiCall Sample 3"
    imageClose="${contextPath}/img/close.png"
    imageMaximize="${contextPath}/img/maximize.png"
    imageMinimize="${contextPath}/img/minimize.png"
    imageRefresh="${contextPath}/img/refresh.png"
    refreshPeriod="30" />
</div> 
</body> 
</html>
