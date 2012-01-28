<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<tiles:definition id="tilesLayoutDirect" template="layout.jsp">
  <tiles:put name="title"  value="My Tiles Title v2" />
  <tiles:put name="header" value="header.jspf" />
  <tiles:put name="body"   value="body.jspf" />
</tiles:definition>
<tiles:insert beanName="tilesLayoutDirect" />