<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="vlh"
	uri="http://valuelist.sourceforge.net/tags-valuelist"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/blue.css" />
<title>Sample03</title>
</head>
<body>
<vlh:root value="list" url="?" configName="classicLook"  includeParameters="*">
	<vlh:retrieve name="allDevelopers" />
	<table width="95%" class="report">
		<tr>
			<th>Developers list</th>
		</tr>
	</table>
	<br />
	<table width="95%" class="simple" cellspacing="0" cellpadding="0">
		<vlh:row bean="dev">
			<vlh:column title="ID" property="did" sortable="desc" />
			<vlh:column title="Name" property="dname" sortable="desc" />
			<vlh:column title="Level" property="dlevel" sortable="desc" />
		</vlh:row>
	</table>
	<vlh:paging />
	<br />
    Export to: &nbsp;&nbsp;
    <vlh:filter url="export.jsp?format=excel&">
      <img src="${pageContext.request.contextPath}/images/export_excel.png" border="0" alt="EXCEL" />
    </vlh:filter>
    <vlh:filter url="export.jsp?format=csv&">
      <img src="${pageContext.request.contextPath}/images/export_csv.png" border="0" alt="CSV"/>
    </vlh:filter>	
</vlh:root>
</body>
</html>
