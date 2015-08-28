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
</head>
<body>

<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">
<a class="navbar-brand" href="ShowPostList">Welcome to Post</a>
</div>
<div>
<ul class="nav navbar-nav navbar-right">
<% if (session.getAttribute("userName") == null) { %>
<li><a href="Signup.jsp">Signup</a></li>
<li><a href="Login.jsp">Login</a></li>
<% } else { %>
<li><a href="User?logout=true">Logout</a></li>
<% } %>
</ul>
</div>
</div>
</nav>
${profile}
<div class="container">
<br />
		<form class="form-horizontal" role="form" name="searchProfilePost"
			id="searchProfilePost" action="ShowProfile" method="post">
			<input type="hidden" name="name" value="${name}">
			<div class="form-group">
				<label class="control-label col-sm-2" for="search">Keywords:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="search" id="search" placeholder="Enter keywords">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default" name="searchProfilePostSub" id="searchProfilePostSub">Search</button>
				</div>
			</div>
		</form>
	</div>
${post}

</body>
</html>