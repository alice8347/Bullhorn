<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Bullhorn</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
function validateForm() {
    var a = document.forms["LoginForm"]["userName"].value;
    var b = document.forms["LoginForm"]["password"].value;

    if (a == null || a == "" || b == null || b == "") {
        alert("All fields must be filled out.");
        return false;
    }
}

function validateUserName() {
	alert("User does not exist.");
}

function validatePassword() {
	alert("Password is incorrect.");
}
</script>
</head>
<body>

<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">
<a class="navbar-brand" href="ShowPostList">Welcome to Post</a>
</div>
<div>
<ul class="nav navbar-nav navbar-right">
<li><a href="Signup.jsp">Signup</a></li>
</ul>
</div>
</div>
</nav>
<div class="container">
<br />
<form class="form-horizontal" role="form" name="LoginForm" id="LoginForm" onsubmit="return validateForm()" action="User" method="post">
<div class="form-group">
<label class="control-label col-sm-2" for="userName">User Name:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="userName" id="userName" placeholder="Enter user name">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="password">Password:</label>
<div class="col-sm-10">
<input type="password" class="form-control" name="password" id="password" placeholder="Enter password">
</div>
</div>
<div class="form-group">
<div class="col-sm-offset-2 col-sm-10">
<button type="submit" class="btn btn-default" name="LoginSub" id="LoginSub">Submit</button>
</div>
</div>
</form>
</div>
${loginErr}

</body>
</html>