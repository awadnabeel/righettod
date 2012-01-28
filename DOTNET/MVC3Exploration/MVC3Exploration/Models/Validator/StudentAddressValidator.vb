Imports System.ComponentModel.DataAnnotations

Namespace Models.Validator

    ''' <summary>
    ''' Custom model validation attribute for StudentAddress data type
    ''' </summary>
    Public Class StudentAddressValidator
        Inherits ValidationAttribute

        ''' <summary>
        ''' Validation method
        ''' </summary>
        ''' <param name="value">Value to validate</param>
        ''' <returns>True only if the value is valid</returns>
        Overrides Function IsValid(ByVal value As Object) As Boolean
            If (IsNothing(value)) Then
                Return False
            End If

            If (Not TypeOf value Is StudentAddress) Then
                Return False
            End If

            Dim add As StudentAddress = CType(value, StudentAddress)

            If (String.IsNullOrWhiteSpace(add.address) OrElse String.IsNullOrWhiteSpace(add.city)) Then
                Return False
            Else
                Return True
            End If

        End Function

    End Class
End Namespace
