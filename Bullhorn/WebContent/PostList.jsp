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
<% if (session.getAttribute("userName") != null) { %>
<div class="container">
<br />
<form class="form-horizontal" role="form" name="addPost" id="addPost" action="ShowPostList" method="post">
<div class="form-group">
<label class="control-label col-sm-2" for="content">Content:</label>
<div class="col-sm-10">
<textarea class="form-control" rows="5" name="content" id="content" placeholder="Enter murmur... "></textarea>
</div>
</div>
<div class="form-group">
<div class="col-sm-offset-2 col-sm-10">
<button type="submit" class="btn btn-default" name="addPostSub" id="addPostSub">Submit</button>
</div>
</div>
</form>
</div>
<% } %>
<div class="container">
<br />
<form class="form-horizontal" role="form" name="searchPost" id="searchPost" action="ShowPostList" method="post">
<div class="form-group">
<label class="control-label col-sm-2" for="search">Keywords:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="search" id="search" placeholder="Enter keywords">
</div>
</div>
<div class="form-group">
<div class="col-sm-offset-2 col-sm-10">
<button type="submit" class="btn btn-default" name="searchPostSub" id="searchPostSub">Search</button>
</div>
</div>
</form>
</div>
${message}
</body>
</html>