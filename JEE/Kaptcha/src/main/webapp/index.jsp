<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test page</title>
    </head>
    <body>
        <!-- 
        We use JQuery to generate another image if the current image is not readable....
        Source : http://code.google.com/p/kaptcha/wiki/HowToUse
        -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script> 
        <form action="${pageContext.request.contextPath}/ValidateCaptcha" method="post">
            <img src="${pageContext.request.contextPath}/GenerateCaptcha" id="kaptchaImage" />
            <script type="text/javascript">
                $(function(){
                    $('#kaptchaImage').click(function () { $(this).attr('src', '${pageContext.request.contextPath}/GenerateCaptcha?' + Math.floor(Math.random()*100) ).fadeIn(); })
                });
            </script>
            <br /><small>Can't read the image? Click it to get a new one.</small>            
            <br />            
            <br />
            <input type="text" name="user_captcha" value="" />
            <br /><br />
            <input type="submit" value="Validate Captcha"/>
        </form>
    </body>
</html>
