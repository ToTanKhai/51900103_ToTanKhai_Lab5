<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tankhai.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Danh sách sản phẩm</title>
<% 
	String fullname = (String) session.getAttribute("fullname");
	ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productList");
	String error = (String) request.getAttribute("error");
	String message = (String) request.getAttribute("message");
	String nameProduct = (String) request.getAttribute("name");
	String priceProduct = (String) request.getAttribute("price");
	
%>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            Xin chào <span class="text-danger"><%= fullname %></span> | <a href="LogoutServlet">Logout</a>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form class="mt-3" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name-product">Tên sản phẩm</label>
                    <input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="name-product" name="name-product" value="<%= nameProduct!=null?nameProduct:"" %>">
                </div>
                <div class="form-group">
                    <label for="price-product">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="price-product" name="price-product" value="<%= priceProduct!=null?priceProduct:"" %>">
                </div>
                <div class="form-group">
                    <button class="btn btn-success mr-2">Thêm sản phẩm</button>
                </div>

                <div class="alert alert-danger">
                    Vui lòng nhập tên sản phẩm
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
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
	                    <% if(products!=null){
	                    	for(Product product : products)
	                    	{
	                    	%>
	                    	<tr>
		                        <td><%= products.indexOf(product)+1 %></td>
		                        <td><a href=""><%= product.name %></a></td>
		                        <td>$<%= product.price %></td>
		                        <td>
		                        	<a href="#">Chỉnh sửa</a> |
		                        	<a href="HomeServlet?id=<%= product.id %>">Xóa</a>
		                        </td>
	                    	</tr> 	
	                    	<%}
	                    }%>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>