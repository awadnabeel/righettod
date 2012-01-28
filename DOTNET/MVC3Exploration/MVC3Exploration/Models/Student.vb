Imports System.ComponentModel.DataAnnotations
Imports MVC3Exploration.Models.Validator
Namespace Models
    ''' <summary>
    ''' Class storing student informations
    ''' 
    ''' Attributes on properties are used to perform "Property" level validation
    ''' </summary>
    Public Class Student

        <Required(AllowEmptyStrings:=False, ErrorMessage:="ID is required")>
        <StringLength(10, ErrorMessage:="ID must have a size betwen 1 and 10", MinimumLength:=1)>
        Public Property id As String

        <Required(AllowEmptyStrings:=False, ErrorMessage:="Name is required")>
        <RegularExpression("^[a-zA-Z]+$", ErrorMessage:="Name must be only alphabetic")>
        Public Property name As String

        Public Property address As StudentAddress

        <Required(AllowEmptyStrings:=False, ErrorMessage:="Phone is required")>
        <PhoneValidator("_", ErrorMessage:="Phone number must be separated by a _")>
        Public Property phone As String

    End Class
End Namespace
