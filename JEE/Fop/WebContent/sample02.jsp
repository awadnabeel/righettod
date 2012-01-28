<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Sample02 : Check AC</title>
<!-- Javascript action -->
<script language="javascript">
function checkAC(){
   
	//IE Check
	if(window.ActiveXObject){
		try {
			var p = eval("new ActiveXObject('AcroPDF.PDF.1');");
			if(p){
			  	return true
			}else{
				return false;
			}
		}
		catch (e) {
		    alert("EXCEPTION FOR IE : " + e.message);
			return false;
		}
	}else{	
		//FIREFOX Check
		if(navigator.plugins){
			try {
			  if(navigator.plugins["Adobe Acrobat"] || navigator.plugins["Adobe PDF Plug-in"] || navigator.plugins["Adobe PDF Plug-in for Firefox and Netscape"]){
			    return true;
			  }else{
			    return false;
			  }
			}
			catch (e) {
			    alert("EXCEPTION FOR FIREFOX : " + e.message);
				return false;
			}	
		}
	}
}
</script>
</head>
<body onload="alert('AC plugin is present on client ' + navigator.appName + ' : ' + checkAC());">
</body>
</html>