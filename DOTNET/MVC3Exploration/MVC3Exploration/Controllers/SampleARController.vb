Namespace Controllers

    ''' <summary>
    ''' Controller exploring behavior of differents "ActionResult" type
    ''' 
    ''' Test urls :
    ''' http://localhost:62943/SampleAR/RedirectAR
    ''' http://localhost:62943/SampleAR/ContentAR
    ''' http://localhost:62943/SampleAR/FileAR
    ''' http://localhost:62943/SampleAR/JsonAR
    ''' http://localhost:62943/SampleAR/HttpUnauthorizedAR
    ''' http://localhost:62943/SampleAR/HttpNotFoundAR
    ''' http://localhost:62943/SampleAR/EmptyAR
    ''' http://localhost:62943/SampleAR/HttpStatusCodeAR
    ''' </summary>
    Public Class SampleARController
        Inherits System.Web.Mvc.Controller

        ''' <summary>
        ''' Explore the "RedirectResult" action result that issues an HTTP 301 or 302 redirection to a specific URL
        ''' </summary>
        ''' <returns>A MVC3 action result</returns>
        Function RedirectAR() As ActionResult
            Return New RedirectResult("http://code.google.com/p/righettod")
        End Function

        ''' <summary>
        ''' Explore the "ContentResult" action result that returns raw textual data to the browser, optionally setting a content-type header
        ''' </summary>
        ''' <returns>A MVC3 action result</returns>
        Function ContentAR() As ActionResult
            Dim contentResult As New ContentResult()
            contentResult.Content = "Hello World !"
            contentResult.ContentType = "text/html"
            Return contentResult
        End Function

        ''' <summary>
        ''' Explore the "FileResult" action result that transmits binary data (such as a file from disk or a byte array in memory) directly to the browser 
        ''' </summary>
        ''' <returns>A MVC3 action result</returns>
        Function FileAR() As FileResult
            Dim filename As String = "D:\DOWNLOAD\Test.pdf"
            Dim contentType As String = "application/pdf"
            Dim downloadName As String = "MyTestFile.pdf"
            Return File(filename, contentType, downloadName)
        End Function

        ''' <summary>
        ''' Explore the "JsonResult" action result that serializes a .NET object in JSON format and sends it as the response 
        ''' </summary>
        ''' <returns>A MVC3 action result</returns>
        Function JsonAR() As ActionResult
            Dim data() As String = {"dog", "cat", "fish"}
            Dim jsonResult As New JsonResult
            jsonResult.ContentType = "application/json"
            jsonResult.Data = data
            jsonResult.JsonRequestBehavior = JsonRequestBehavior.AllowGet
            Return jsonResult
        End Function

        ''' <summary>
        ''' Explore the "HttpUnauthorizedResult" action result that sets the response HTTP status code to 401 (meaning “not authorized”), 
        ''' which causes the active authentication mechanism (forms authentication or Windows authentication) 
        ''' to ask the visitor to log in
        ''' </summary>
        ''' <returns>A MVC3 action result</returns>
        Function HttpUnauthorizedAR() As ActionResult
            Return New HttpUnauthorizedResult
        End Function

        ''' <summary>
        ''' Explore the "HttpNotFoundResult" action result that returns a HTTP 404 – Not found error
        ''' </summary>
        ''' <returns>A MVC3 action result</returns>
        Function HttpNotFoundAR() As ActionResult
            Return New HttpNotFoundResult
        End Function

        ''' <summary>
        ''' Explore the "EmptyResult" action result that does nothing ;o)
        ''' </summary>
        ''' <returns>A MVC3 action result</returns>
        Function EmptyAR() As ActionResult
            Return New EmptyResult
        End Function

        ''' <summary>
        ''' Explore the "HttpStatusCodeResult" action result that returns a specified HTTP code 
        ''' </summary>
        ''' <returns>A MVC3 action result</returns>
        Function HttpStatusCodeAR() As ActionResult
            Return New HttpStatusCodeResult(202, "Your request is accepted ;o)")
        End Function

    End Class
End Namespace
