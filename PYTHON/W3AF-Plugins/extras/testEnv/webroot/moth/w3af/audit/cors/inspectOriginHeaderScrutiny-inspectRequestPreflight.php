<?
if (array_key_exists('SetAccessControlAllowOriginValue', $_GET)) {
	$AccessControlAllowOriginValue = $_GET["SetAccessControlAllowOriginValue"];
} else {
	$AccessControlAllowOriginValue = "http://google.fr";
}
header("Access-Control-Allow-Origin: " . $AccessControlAllowOriginValue);
?>
<html>
	<body>
		Http Response code 200 returned.<br>
		<b>Access-Control-Allow-Origin</b> http response header set to "<?php echo($AccessControlAllowOriginValue); ?>" 
	</body>
</html>