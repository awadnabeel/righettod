<?php
//Load xml data container
$xml        = simplexml_load_file("agenda.xml");

//Retrieve agenda global informations
$rootAttr   = $xml->attributes(); 
$lastUpdate = $rootAttr["lastUpdate"];

//Retrieve list of day label
$i = 1;
$dayLabels = array(); 
foreach ($xml->xpath("/agenda/day") as $cday) {
		$dayAttr  = $cday->attributes();
		$dayLabel = $dayAttr["label"];
		$dayLabels[$i] = $dayLabel;
		$i++; 	
}

//Retrieve advertisement information
$advertisement = trim($xml->advertisement);
?>

<!DOCTYPE html> 
<html> 
	<head> 
	<title>Flirty Timetable</title> 
	
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<meta name="copyright" content="Flirty Fitness Luxemburg">
	<meta http-equiv="content-language" content="en">
	<meta name="rating" content="general">
	<meta name="keywords" content="flirty,fitness,timetable,pole,dance,sport,sexy,girl,zumba">
	<meta name="abstract" content="Flirty Fitness timetable for mobile">
	<meta name="title" content="Flirty Fitness timetable for mobile">
	<meta name="revisit-after" content="2">
	<meta name="robots" content="index,follow">
	<meta name="description" content="Flirty Fitness timetable for mobile">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="author" content="Dominique Righetto (dominique.righetto@gmail.com)">
	<meta name="designer" content="Amelie Righetto (amelie.righetto@gmail.com)">	

	<!-- Use JQuery Mobile design using CDN stored CSS and images -->
	<!-- <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0b3/jquery.mobile-1.0b3.min.css" /> -->
	<!-- Use Flirty Fitness design using local stored and modified CSS and images  -->
	<link rel="stylesheet" href="css/jquery.mobile-1.0b3-ff-theme.min.css" />
	
	<!-- Use JQuery Mobile and JQuery JS(s) stored on JQuery CDN  -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.3.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/mobile/1.0b3/jquery.mobile-1.0b3.min.js"></script>
	
	<!-- Google Analytics Tracker -->
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-26199052-1']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>		
</head> 
<body> 

<!-- Welcome page -->
<div data-role="page" id="P0" data-dom-cache="true" data-theme="b">

	<div data-role="header" data-theme="b">
		<h1><img src="css/images/logo.png" border="0" /><br />Flirty Timetable</h1>
	</div>

	<div data-role="content" data-theme="b">	
		<p>Welcome on the Flirty Fitness timetable for mobile.</p>
		<p>Bienvenue sur le planning Flirty Fitness pour mobile.</p>	
		<p><a href="#P1" id="planningAccessLink" data-role="button"  data-icon="grid" data-iconpos="right" data-transition="fade">Planning / Timetable</a></p>
		<div class="ui-footer ui-bar-e">
			<p style="text-align:center;padding-left:3px;"><?php echo($advertisement); ?></p>
		</div>		
	</div>

	<div data-role="footer" data-theme="b">
		<p style="text-align:center;padding-left:3px;">Upd - Maj : <?php echo($lastUpdate); ?></p>
	</div>
</div>


<!-- Agenda pages by day -->
<?php

//Generate one page by day using order
for($i = 1 ; $i <= 7 ; $i++ ){
	foreach ($xml->xpath("/agenda/day[@order='" . $i . "']") as $cday) {
		$dayAttr  = $cday->attributes();
		$dayLabel = $dayAttr["label"];

		//PAGE
		echo("<div data-role=\"page\" data-dom-cache=\"true\" id=\"P$i\" data-theme=\"b\">");		
		
		//HEADER WITH NAVIGATION BUTTON
		echo("<div data-role=\"header\" data-theme=\"b\" data-position=\"fixed\">");
		echo("<h4><img src=\"css/images/logo.png\" border=\"0\" /><br />" . str_replace("-", "<br />", $dayLabel) . "</h4>");
		echo("<div data-role=\"navbar\">");
		echo("<ul>");
		if($i == 1){
			$linkId = 7;
			echo("<li><a href=\"#P$linkId\" data-icon=\"arrow-l\" data-role=\"button\" data-transition=\"fade\">$dayLabels[$linkId]</a></li>");
		}		
		if($i > 1){
			$linkId = ($i - 1);
			echo("<li><a href=\"#P$linkId\" data-icon=\"arrow-l\" data-role=\"button\" data-transition=\"fade\">$dayLabels[$linkId]</a></li>");
		}
		echo("<li><a href=\"#P0\" data-icon=\"home\" data-role=\"button\" data-transition=\"fade\">Home</a></li>");
		if($i < 7){
			$linkId = ($i + 1);
			echo("<li><a href=\"#P$linkId\" data-icon=\"arrow-r\" data-role=\"button\" data-transition=\"fade\">$dayLabels[$linkId]</a></li>");
		}
		if($i == 7){
			$linkId = 1;
			echo("<li><a href=\"#P$linkId\" data-icon=\"arrow-r\" data-role=\"button\" data-transition=\"fade\">$dayLabels[$linkId]</a></li>");
		}		
		echo("</ul>");				
		echo("</div>");
		echo("</div>");

		//CONTENT BY TIME
		echo("<div data-role=\"content\" data-theme=\"b\">");
		echo("<div data-role=\"collapsible-set\">");
		$j = 0;
		$previousCTime = "";
		foreach ($xml->xpath("/agenda/day[@order='" . $i . "']/class") as $cclass) {
			$classAttr   = $cclass->attributes();
			$ctimeTmp    = explode('-',$classAttr["time"]);
			$ctime       = trim($ctimeTmp[0]);
			$realTime    = $classAttr["time"];
			$clabel      = $classAttr["label"];
			$clevel      = $classAttr["level"];
			$cstudio     = $classAttr["studio"];
			$cinstructor = $classAttr["instructor"];
			
			//Check for closing previous time DIV		
			if(strcmp($ctime,$previousCTime) != 0 && $j > 0){
				//Close previous time DIV
				echo("</div>");
			}else{
				//Separate informations into the same time DIV
				echo("<br /><br />");
			}			
			
			//Open new DIV only if the time is different from the previous
			//the goal is to group time by box...
			if($j==0){
				if(strcmp($ctime,$previousCTime) != 0){
					echo("<div data-role=\"collapsible\" data-collapsed=\"false\">");
					echo("<h3>$ctime</h3>");
				}
			}else{
				if(strcmp($ctime,$previousCTime) != 0){
					echo("<div data-role=\"collapsible\" data-collapsed=\"true\">");
					echo("<h3>$ctime</h3>");
				}	
			}

			echo("<strong>$clabel ($realTime)</strong><br />");
			echo("Level: $clevel<br />");
			echo("Studio: $cstudio<br />");
			//echo("Prof.: $cinstructor");

			$j++;
			$previousCTime = $ctime;
		}
		//Close final time DIV
		echo("</div>");
		
		echo("</div>");
		echo("</div>");
			
		//FOOTER
		echo("<div data-role=\"footer\" data-theme=\"b\">");
		echo("<p style=\"text-align:center;padding-left:3px;\">Upd - Maj : $lastUpdate</p>");
		echo("</div>");
		
		//END OF PAGE
		echo("</div>");		
	}
}

?>

 
<script type="text/javascript">
	//Determine opening day for planning button link on welcome page
	var cClientSideDay = new Date().getDay(); 
	//Manage 'Sunday' day case because 
	//we start from 1 (Monday) to 7 (Sunday) and JS Date function 
	//start from 0 (Sunday) to 6 (Saturday)
	if(Number(cClientSideDay) == 0){
		cClientSideDay = 7;
	}	
	$("#planningAccessLink").attr("href", "#P" + (cClientSideDay));
</script>
</body>
</html>
