Imports System.ComponentModel.DataAnnotations

Namespace Models.Validator

    ''' <summary>
    ''' Custom property validation attribute for phone number data type
    ''' </summary>
    Public Class PhoneValidator
        Inherits ValidationAttribute

        Private separatorCharacter As String = "-"

        ''' <summary>
        ''' Construstor
        ''' </summary>
        ''' <param name="separatorCharacter">Character that separate number group</param>
        Public Sub New(ByVal separatorCharacter As String)
            Me.separatorCharacter = separatorCharacter
        End Sub

        ''' <summary>
        ''' Validation method
        ''' </summary>
        ''' <param name="value">Value to validate</param>
        ''' <returns>True only if the value is valid</returns>
        Overrides Function IsValid(ByVal value As Object) As Boolean
            If (value.ToString.Contains(Me.separatorCharacter)) Then
                Return True
            Else
                Return False
            End If
        End Function

    End Class
End Namespace
