package com.righettod.kaptcha;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 * Servlet to validate captcha information submitted by the user against captch value expected
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
@WebServlet(name = "ValidateCaptcha", urlPatterns = {"/ValidateCaptcha"})
public class ValidateCaptcha extends HttpServlet {

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get captcha information submitted by the user
        String userCaptchaValue = request.getParameter("user_captcha");

        //Get captcha information expected using KAPTCHA framework
        String captchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        //Get datetime age of the current generated captcha using KAPTCHA framework
        Date captchaAge = (Date) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_DATE);

        //Prepare response headers
        response.setContentType("text/html");

        //Perform validation
        String msg;
        if (StringUtils.isEmpty(userCaptchaValue) || !userCaptchaValue.equals(captchaExpected)) {
            msg = "<font color='red'>Captcha value do not match !</font>";
        } else {
            msg = "<font color='green'>Captcha value match !</font>";
        }

        //Send response content
        response.getWriter().print("<html><body>");
        response.getWriter().print(msg);
        response.getWriter().print("<br />");
        response.getWriter().print("Captcha generated at : " + new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:S").format(captchaAge));
        response.getWriter().print("</body></html>");
    }
}
