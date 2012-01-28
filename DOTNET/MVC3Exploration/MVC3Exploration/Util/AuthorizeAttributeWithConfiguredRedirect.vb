''' <summary>
''' Custom implementation of a "Authorize" attribute that offer possbility to configure target redirection in case of non allowed access.
''' </summary>
Public Class AuthorizeAttributeWithConfiguredRedirect
    Inherits AuthorizeAttribute

    Private nonAllowedRedirectUrl As String

    Public Sub New(ByVal nonAllowedRedirectUrl As String)
        Me.nonAllowedRedirectUrl = nonAllowedRedirectUrl
    End Sub


    ''' <summary>
    ''' Implementation of the management in case of unauthorized access to a controller/action.
    ''' 
    ''' Perform a redirection to the url specified into the attribute property named "nonAllowedRedirectUrl"
    ''' </summary>
    ''' <param name="filterContext">Context</param>
    Protected Overrides Sub HandleUnauthorizedRequest(filterContext As AuthorizationContext)
        filterContext.Result = New RedirectResult(Me.nonAllowedRedirectUrl)
    End Sub



End Class