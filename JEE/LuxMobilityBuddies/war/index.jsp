<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <head>
        <title>Buddies for IPhone and IPad devices providing mobility services for Luxemburg town (parking,traffic infos,...)</title>
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
        <%-- Main panel with parking list --%>
		<div id="home" class="current">
            <h2>Mobility buddies list</h2>
            <ul class="rounded">
                <li class="forward"><a target="_webapp" href="parking/main.jsp">Parking buddy</a></li>
                <li class="forward"><a target="_webapp" href="tunnelhowald/index.html">Tunnel Howald buddy</a></li>
                <li class="forward"><a href="http://trafficinfolux.appspot.com/imobile/index.jsp" target="_blank">Traffic informations buddy</a></li>
            </ul>            
	        <div class="info">
	          <p>All informations used by buddies are provided by the Luxemburg town (http://www.vdl.lu) and CITA (http://www.cita.lu)</p>
	        </div>        
        </div>
        
</body>
</html>