@Imports MVC3Exploration.Models
@Imports MVC3Exploration.Repositories
@ModelType MVC3Exploration.Models.Student

@Code
    ViewData("Title") = "Index"
    ViewData("Students") = New StudentRepository().RetrieveAllStudents
End Code


<!-- Display list of students -->
<h2>Student list</h2>
<table width="100%" border="1" >
    <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>ADDRESS</th>
            <th>CITY</th>
        </tr>
    </thead> 
    <tbody>
@For Each std As Student In ViewData("Students")
    @<text>
        <tr>
            <td>@std.id</td>
            <td>@std.name</td>
            <td>@std.address.address</td>
            <td>@std.address.city</td>
        </tr>
    </text>    
Next
    </tbody> 
</table>

<br /><br /><br />

<!-- Create a form to add a model instance -->
<!--
<h2>Form to add a student - Version 1</h2>
@Using (Html.BeginForm("Add", "SampleModelBinding", FormMethod.Post, New With {.id = "AddFrmV1"}))
    @<fieldset>
    @* Create a automatic editor for the main model *@
    @Html.EditorForModel() 
    @* Create a editor for 'address' property of the the main model that is a custom object *@
    @Html.EditorFor(Function(model) model.address)    
    <input type="submit" value="Add" id="submit1"/> 
    </fieldset>
End Using 
-->

<br /><br /><br />

<!-- Create a form to add a model instance -->
<h2>Form to add a student - Version 2</h2>
<!-- Display validation summary for "Model" and "Property" level validations -->
@Html.ValidationSummary(False) 
@Using (Html.BeginForm("Add", "SampleModelBinding", FormMethod.Post, New With {.id = "AddFrmV2"}))
    @<fieldset>
    <legend>Student</legend>
    @* Create a manual editor for the main model and sub object *@  
        <div class="editor-label">@Html.LabelFor(Function(model) model.id)</div>
        <div class="editor-field">@Html.EditorFor(Function(model) model.id) @Html.ValidationMessageFor(Function(model) model.id)</div>

        <div class="editor-label">@Html.LabelFor(Function(model) model.name)</div>
        <div class="editor-field">@Html.EditorFor(Function(model) model.name) @Html.ValidationMessageFor(Function(model) model.name)</div>

        <div class="editor-label">@Html.LabelFor(Function(model) model.phone)</div>
        <div class="editor-field">@Html.EditorFor(Function(model) model.phone) @Html.ValidationMessageFor(Function(model) model.phone)</div>

        <div class="editor-label">@Html.LabelFor(Function(model) model.address.address)</div>
        <div class="editor-field">@Html.EditorFor(Function(model) model.address.address)</div>

        <div class="editor-label">@Html.LabelFor(Function(model) model.address.city)</div>
        <div class="editor-field">@Html.EditorFor(Function(model) model.address.city)</div>
    <input type="submit" value="Add" id="submit2"/> 
    </fieldset>
End Using 