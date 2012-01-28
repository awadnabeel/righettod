Imports MVC3Exploration.Models

Namespace Repositories

    ''' <summary>
    ''' Class managing all actions on STUDENT model instances
    ''' </summary>
    Public Class StudentRepository

        ''' <summary>
        ''' Student memory storage
        ''' </summary>
        Private Shared ReadOnly studentsStorage As New List(Of Student)

        ''' <summary>
        ''' Method a new student into the storage
        ''' </summary>
        ''' <param name="student">Student model instance to add</param>
        Public Sub AddStudent(ByRef student As Student)
            studentsStorage.Add(student)
        End Sub

        ''' <summary>
        ''' Method to retrieve the list of all students
        ''' </summary>
        ''' <returns>The list of students model instance</returns>
        Public Function RetrieveAllStudents() As List(Of Student)
            Return studentsStorage
        End Function

        ''' <summary>
        ''' Method to retrieve a students
        ''' </summary>
        ''' <returns>The student model instance</returns>
        Public Function RetrieveStudent(ByVal studentId As String) As Student
            For Each s As Student In studentsStorage
                If s.id.Equals(studentId) Then
                    Return s
                End If
            Next
            Return Nothing
        End Function

    End Class
End Namespace
