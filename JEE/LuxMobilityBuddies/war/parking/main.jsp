<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <head>
        <title>Parking buddy for IPhone and IPad devices</title>
		<meta charset="UTF-8" />
		<style type="text/css" media="screen">@import "${pageContext.request.contextPath}/jqtouch/jqtouch.min.css";</style>
		<style type="text/css" media="screen">@import "${pageContext.request.contextPath}/themes/jqt/theme.min.css";</style>
		<script src="${pageContext.request.contextPath}/jqtouch/jquery.1.3.2.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/jqtouch/jqtouch.min.js" type="application/x-javascript" charset="utf-8"></script>
        <script type="text/javascript" charset="utf-8">
            var jQT = new $.jQTouch({
                icon: 'parking.png',
                addGlossToIcon: false,
                startupScreen: 'parking_startup.png',
                statusBar: 'black',
                preloadImages: [
                    '${pageContext.request.contextPath}/themes/jqt/img/back_button.png',
                    '${pageContext.request.contextPath}/themes/jqt/img/back_button_clicked.png',
                    '${pageContext.request.contextPath}/themes/jqt/img/button_clicked.png',
                    '${pageContext.request.contextPath}/themes/jqt/img/grayButton.png',
                    '${pageContext.request.contextPath}/themes/jqt/img/whiteButton.png',
                    '${pageContext.request.contextPath}/themes/jqt/img/loading.gif',
                    'parking.png',
                    '${pageContext.request.contextPath}/themes/ximg/ok.png',
                    '${pageContext.request.contextPath}/themes/ximg/ko.png',
                    '${pageContext.request.contextPath}/themes/ximg/up.png',
                    '${pageContext.request.contextPath}/themes/ximg/down.png',
                    '${pageContext.request.contextPath}/themes/ximg/equals.png'
                    ]
            });
        </script>
		<style type="text/css" media="screen">
		    body.fullscreen #home .info {
		        display: none;
		    }
		    #about {
		        padding: 100px 10px 40px;
		        text-shadow: rgba(255, 255, 255, 0.3) 0px -1px 0;
		        font-size: 13px;
		        text-align: center;
		        background: #161618;
		    }
		    #about p {
		        margin-bottom: 8px;
		    }
		    #about a {
		        color: #fff;
		        font-weight: bold;
		        text-decoration: none;
		    }
		</style>        
    </head>
<body>	
		<%-- Panel with about informations --%>
        <div id="about" class="selectable">
                <p><img src="parking.png" /></p>
                <p><strong>Parking buddy for IPhone, IPad and Android devices using JQTouch</strong></p>
                <p>Developed by Dominique Righetto</p>
                <p><a href="http://code.google.com/p/drighetto-it-stay" target="_blank">My GoogleCode repository</a></p>
                <p><a href="http://www.vdl.lu" target="_blank">Luxemburg town homepage</a></p>
                <p><a href="http://www.jqtouch.com" target="_blank">JQTouch homepage</a></p>
                <p><br /><br /><a href="#" class="grayButton goback">Close</a></p>
        </div>
        
        <%-- Main panel with parking list --%>
		<div id="home" class="current">
            <div class="toolbar">
                <h1>Parking buddy</h1>
                <a class="button slideup" id="infoButton" href="#about">About</a>
            </div>
            <h2>${I18N['title']}</h2>
            <ul class="plastic">
            	<c:forEach var="pkg" items="${PARKING}">
	            	<li class="arrow">
		            	<c:choose>
		            		<%-- Parking not full AND open --%>
		            		<c:when test="${pkg.fillingStatus != 1 and pkg.open == 1}"><a href="details.jsp?pname=${pkg.name}"><img src="../themes/ximg/ok.png" border="0" /> ${pkg.name} <font size="-2">(${pkg.available} ${I18N['place.available']})</font></a></c:when>
		            		<%-- Parking full --%>
		            		<c:when test="${pkg.fillingStatus == 1}"><a href="details.jsp?pname=${pkg.name}"><img src="../themes/ximg/ko.png" border="0" /> ${pkg.name} <font size="-2">(${I18N['pkg.full']})</font></a></c:when>
		            		<%-- Parking not open --%>
		            		<c:when test="${pkg.open != 1}"><a href="details.jsp?pname=${pkg.name}"><img src="../themes/ximg/ko.png" border="0" /> ${pkg.name} <font size="-2">(${I18N['pkg.notopen']})</font></a></c:when>
		            	</c:choose>
	            	</li>            		
            	</c:forEach>
            </ul>              
	        <div class="info">
	          <p>${I18N['info.dataprovider']}</p>
	        </div>        
        </div>
        
</body>
</html>