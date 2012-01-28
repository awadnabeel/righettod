<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="com.cychop.til.enums.Driveway,java.util.ArrayList,com.cychop.til.duration.Duration,com.cychop.til.duration.InstantDuration,com.cychop.til.gapp.InfoGetter,com.google.apphosting.api.ApiProxy"%>
<!doctype html>
<html>
    <head>
       <meta charset="UTF-8" />
       <title>TrafficInfoLux WUI for IPhone and IPad devices</title>
       <style type="text/css" media="screen">@import "jqtouch/jqtouch.min.css";</style>
       <style type="text/css" media="screen">@import "themes/jqt/theme.min.css";</style>
       <script src="jqtouch/jquery.1.3.2.min.js" type="text/javascript" charset="utf-8"></script>
       <script src="jqtouch/jqtouch.min.js" type="application/x-javascript" charset="utf-8"></script>
       <script type="text/javascript" charset="utf-8">
           var jQT = new $.jQTouch({
               icon: 'jqtouch.png',
               addGlossToIcon: true,
               startupScreen: 'jqt_startup.png',
               statusBar: 'black',
               preloadImages: [
                   'themes/jqt/img/back_button.png',
                   'themes/jqt/img/back_button_clicked.png',
                   'themes/jqt/img/button_clicked.png',
                   'themes/jqt/img/grayButton.png',
                   'themes/jqt/img/whiteButton.png',
                   'themes/jqt/img/loading.gif'
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
            
            .FLUID { color: #00ff00; }
            .DENSE { color: #ffcc00; }
            .SATURATED { color: #ff0000; }
        </style>
    </head>
    <body>
        <!-- Panel with about informations -->
        <div id="about" class="selectable">
            <p><img src="jqtouch.png" /></p>
            <p><strong>TrafficInfoLux WUI for iPhone, iPad and Android devices using JQTouch</strong><br />
            Version <%=ApiProxy.getCurrentEnvironment().getVersionId()%></p>
            <p>Developed by Cyrille Chopelet &amp; Dominique Righetto</p>
            <p><a href="http://code.google.com/p/drighetto-it-stay" target="_blank">Our GoogleCode repository</a></p>
            <p><a href="http://jqtouch.com" target="_blank">JQTouch Homepage</a></p>
            <p><br /><br /><a href="#" class="grayButton goback">Close</a></p>
        </div>
        <!-- Main panel with driveways list -->
        <div id="home" class="current">
            <div class="toolbar">
                <h1>TrafficInfoLux</h1>
                <a class="button slideup" id="infoButton" href="#about">About</a>
            </div>
            <h2>Driveways list</h2>
            <ul class="rounded">
                <%
                    for (Driveway d : Driveway.values()) {
                        out.print("\t\t\t<li class=\"arrow\"><a href=\"#" + d.name() + "\">"
                                + d.name() + "</a></li>");
                    }
                %>
            </ul>
        </div>
        <!-- Trip details -->
    <%
        ArrayList<Duration> tripDetails = InfoGetter.getCitaInfo();
        Driveway currentD = null;
        
        for (Duration d : tripDetails) {
        	String htmlSource = "";
            if (d.getTrip().getDriveway() != currentD) {
                if (currentD != null) {
                    htmlSource += "\t\t</ul>\n"
                        + "\t\t<div class=\"info\">The data is provided by the "
                        + "<a href=\"http://www.cita.lu/\">CITA</a>.</div>\n"
                        + "\t</div>\n";
                }
                currentD = d.getTrip().getDriveway();
                htmlSource += "\t<div id=\"" + currentD.name() + "\">\n"
                    + "\t\t<div class=\"toolbar\"><h1>" + currentD.name()
                    + "</h1></div>\n"
                    + "\t\t<a class=\"back\" href=\"#\">Back</a>\n"
                    + "\t\t<ul class=\"rounded\">\n";
            }
            htmlSource += "\t\t\t<li><a>" + d.getTrip().getStartPoint()
                + " to " + d.getTrip().getEndinPoint() + ": <span"
                + ((d instanceof InstantDuration) ? " class=\"" + ((InstantDuration) d).getFlow() + "\">" : ">")
                + ((d.getDuration() == -1) ? "FLUID" : (d.getDuration() + " min."))
                + "</span></a></li>\n";
            
           out.print(htmlSource);
        }
    
        out.print("\t\t</ul>\n");
        out.print("\t\t<div class=\"info\">The data is provided by the <a href=\"http://www.cita.lu/\">CITA</a>\n");
        out.print("\t</div>\n\n");
    %>
    </body>
</html>