<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Login Page</title>
</head>
<body>
<% 
	String error = (String) request.getAttribute("error");
	String username = (String) request.getAttribute("username");
%>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h3 class="text-center text-secondary mt-5 mb-3">User Login</h3>
            <form method="post" action="LoginServlet" class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input id="username" name="username" type="text" class="form-control" placeholder="Username" value="<%= username!=null?username:"" %>">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input id="password" name="password" type="password" class="form-control" placeholder="Password">
                </div>
                <% if(error != null){
                	%>
                	<div class="alert alert-danger">
                        <%= error %>
                    </div>
                	<%              	                    	
                }%>
                <div class="form-group">
                	<input type="checkbox" name="remember"> Remember username and password
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success px-5">Login</button>
                </div>
                <div class="form-group">
                    <a href="register.jsp" class="btn btn-outline-success px-5">Register</a>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>