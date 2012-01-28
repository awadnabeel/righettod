<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insert template="layout.jsp">
  <tiles:put name="title"  value="My Tiles Title v1" />
  <tiles:put name="header" value="header.jspf" />
  <tiles:put name="body"   value="body.jspf" />
</tiles:insert>