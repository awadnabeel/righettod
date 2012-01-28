<%@ taglib prefix="vlh" uri="http://valuelist.sourceforge.net/tags-valuelist" %>
<%
/*
 * Page used to export data to another format : Excel, CSV,... 
 * Target export format is set by a request parameter named "format", 
 * if it isn't set, default format is HTML...
 *
 * Note : tab and space before and after <vlh:column /> are wrote to exported document !!!
 */
String format = (request.getParameter("format") == null) ? "html" : (String)request.getParameter("format");
%>
<vlh:root configName="classicLook" value="list" url="?" includeParameters="*" >
<vlh:retrieve name="allDevelopers" />
<vlh:row display="<%=format %>" bean="dev">
<vlh:column title="ID" property="did"  />
<vlh:column title="Name" property="dname"  />
<vlh:column title="Level" property="dlevel"  />
</vlh:row>
</vlh:root>