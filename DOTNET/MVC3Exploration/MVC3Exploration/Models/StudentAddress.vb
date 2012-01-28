Imports MVC3Exploration.Models.Validator
Namespace Models

    ''' <summary>
    ''' Class storing student address informations
    ''' 
    ''' Attribute on class is used to perform "Model" level validation
    ''' </summary>
    <StudentAddressValidator(ErrorMessage:="Student address is not valid !")>
    Public Class StudentAddress
        Public Property address As String
        Public Property city As String
    End Class
End Namespace
