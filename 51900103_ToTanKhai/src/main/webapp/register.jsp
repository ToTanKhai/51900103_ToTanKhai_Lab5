<%@page import="org.apache.catalina.tribes.ChannelSender"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
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
<title>Insert title here</title>
</head>
<body>
<% 
	String error = (String) request.getAttribute("error");
	String message = (String) request.getAttribute("message");
	String fullname = (String) request.getAttribute("fullname");
	String username = (String) request.getAttribute("username");
%>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h3 class="text-center text-secondary mt-5 mb-3">User Register</h3>
            <form class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light" action="RegisterServlet" method="POST">
                <div class="form-group">
                    <label for="fullname">Fullname</label>
                    <input name="fullname" id="fullname" type="text" class="form-control" placeholder="Your Name" value="<%= fullname!=null?fullname:"" %>">
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input name="username" id="username" type="text" class="form-control" placeholder="Username" value="<%= username!=null?username:"" %>">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input name="password" id="password" type="password" class="form-control" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="confirm-password">Confirm Password</label>
                    <input name="confirm-password" id="confirm-password" type="password" class="form-control" placeholder="Password">
                </div>
               
               
                <% if(error != null){
                	%>
                	<div class="alert alert-danger">
                        <%= error %>
                    </div>
                	<%              	
                	} 
                	if(message != null){
                	%>
                	<div class="alert alert-success">
                        <%= message %>
                    </div>
                	<%
                }%>
               
                <div class="form-group">
                    <button class="btn btn-success px-5">Register</button>
                    <a href="login.jsp" class="btn btn-outline-success px-5">Login</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>