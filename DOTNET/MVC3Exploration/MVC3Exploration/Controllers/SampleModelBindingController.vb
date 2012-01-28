Imports MVC3Exploration.Repositories
Imports MVC3Exploration.Models

Namespace Controllers

    ''' <summary>
    ''' Controller exploring model object binding between VIEW and CONTROLLER layers
    ''' 
    ''' Test url : http://localhost:62943/SampleModelBinding/Index
    ''' </summary>
    Public Class SampleModelBindingController
        Inherits System.Web.Mvc.Controller

        Private studentsRepository As New StudentRepository()

        ''' <summary>
        ''' Controller home action
        ''' </summary>
        ''' <returns>an MVC3 redirection</returns>
        Function Index() As ActionResult
            Return (View())
        End Function

        ''' <summary>
        ''' Controller action to add a student
        ''' </summary>
        ''' <param name="student">Student to ass</param>
        ''' <returns>an MVC3 redirection</returns>
        Function Add(ByVal student As Student) As ActionResult

            'Perform "Model" validation level explicitly
            If (IsNothing(student.address)) Then
                ModelState.AddModelError("", "Address is required !")
            ElseIf (String.IsNullOrWhiteSpace(student.address.address) OrElse String.IsNullOrWhiteSpace(student.address.city)) Then
                ModelState.AddModelError("", "All address informations are required !")
            End If

            'Add student only if model is valid
            If (ModelState.IsValid) Then
                studentsRepository.AddStudent(student)
            End If

            'Redirect to the home controller action 
            Return View("Index", student)
        End Function

    End Class
End Namespace
