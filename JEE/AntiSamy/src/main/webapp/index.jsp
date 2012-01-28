<html>
<head>
<title>AntiSamy API Exploration</title>
</head>
<body>
	<form action="/AntiSamy/sanitize" method="post">
		<h3>Enter a text to sanitize:</h3>
		<textarea rows="10" cols="150" name="input">
			<div>Hello<script>alert('toto()');</script>Dominique<scRipt>document.cookies;</ScRipt><style>.myclass{width:100px;}</style>   
		</textarea>
		<br><br>
		<input type="submit" value="Sanitize !">
	</form>
</body>
</html>
