<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>FOP Samples</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/PDFGenerator">SAMPLE00 : Display PDF without included JS</a>
<br />
<a href="${pageContext.request.contextPath}/PDFGeneratorWithJS">SAMPLE00 : Display PDF with included JS</a>
<br />
<a href="#" onclick="javascript:document.getElementById('pdfContainer').src='http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/PDFGeneratorWithJS';">SAMPLE01 : Direct Printing</a>
<br />
<a href="${pageContext.request.contextPath}/sample02.jsp">SAMPLE02 : Check Acrobat Reader presence</a>

<!-- PDF object container no visible to user because PDF file have printing JS code embedded -->
<iframe id="pdfContainer" frameborder="0" width="0" height="0" src="" />
</body>
</html>