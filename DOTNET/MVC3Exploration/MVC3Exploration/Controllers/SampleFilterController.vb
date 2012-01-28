Namespace MVC3Exploration

    ''' <summary>
    ''' Controller exploring behavior of differents MVC3 filters
    ''' 
    ''' Test urls :
    ''' http://localhost:62943/SampleFilter/HelloSecured?login=righettod
    ''' http://localhost:62943/SampleFilter/HelloNotSecured
    ''' http://localhost:62943/SampleFilter/HelloWithError
    ''' http://localhost:62943/SampleFilter/HelloSecuredCustomAttr?login=righettod
    ''' http://localhost:62943/SampleFilter/HelloSecuredCustomAttr
    ''' </summary>
    Public Class SampleFilterController
        Inherits System.Web.Mvc.Controller


        ''' <summary>
        ''' Sample action that, when is reached, show behavior of "Authorize" built-in attribute
        ''' 
        ''' This action require that user will be authenticated and possess role "ADMIN"
        ''' </summary>
        ''' <returns>A MVC3 redirection</returns>
        <Authorize(Roles:="ADMIN")>
        Function HelloSecured() As ActionResult
            Dim contentResult As New ContentResult()
            contentResult.Content = "Hello '" + HttpContext.User.Identity.Name + "'"
            contentResult.ContentType = "text/html"
            Return contentResult
        End Function

        ''' <summary>
        ''' Sample action that, when is reached, show behavior of user identity access
        ''' </summary>
        ''' <returns>A MVC3 redirection</returns>
        Function HelloNotSecured() As ActionResult
            Dim contentResult As New ContentResult()
            If (HttpContext.User.Identity.IsAuthenticated) Then
                contentResult.Content = "Hello '" + HttpContext.User.Identity.Name + "'"
            Else
                contentResult.Content = "Hello 'anonymous' (not authenticated)"
            End If
            contentResult.ContentType = "text/html"
            Return contentResult
        End Function

        ''' <summary>
        ''' Sample action that, when is reached, throw a NullReferenceException that invoke "HandleError" attribute processing
        ''' </summary>
        ''' <returns>A MVC3 redirection</returns>
        <HandleError(ExceptionType:=GetType(System.NullReferenceException), View:="Error")>
        Function HelloWithError() As ActionResult
            Dim contentResult As ContentResult
            contentResult.Content = "Hello '" + HttpContext.User.Identity.Name + "'"
            contentResult.ContentType = "text/html"
            Return contentResult
        End Function

        ''' <summary>
        ''' Sample action that, when is reached, show behavior of "Authorize" custom impl. attribute
        ''' 
        ''' This action require that user will be authenticated and possess role "ADMIN" and use a custom "Authorize" attribute implementation
        ''' </summary>
        ''' <returns>A MVC3 redirection</returns>
        <AuthorizeAttributeWithConfiguredRedirect("http://www.google.fr", Roles:="ADMIN")>
        Function HelloSecuredCustomAttr() As ActionResult
            Dim contentResult As New ContentResult()
            contentResult.Content = "Hello '" + HttpContext.User.Identity.Name + "'"
            contentResult.ContentType = "text/html"
            Return contentResult
        End Function

    End Class
End Namespace
