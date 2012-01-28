﻿@Model HandleErrorInfo 
@Code 
    ViewBag.Title = "Sorry, there was a problem!" 
End code

<h1>My Custom Error Management Page ;o)</h1>
<p> 
    There was a <b>@Model.Exception.GetType().Name</b> 
    while rendering <b>@Model.ControllerName</b>'s 
<b>@Model.ActionName</b> action. 
</p> 
<p> 
    The exception message is: <b><@Model.Exception.Message></b> 
</p> 
<p>Stack trace:</p> 
<pre>@Model.Exception.StackTrace</pre>