<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.barrysboard.model.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=devicewidth, initial-scale=1.0" />
<link rel="stylesheet" href="css/Main.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="icon" type="image/gif" href="images/barrysboard.ico">
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<![endif]-->
<style>
	.jumbotron {
	background-color: #333;
	color: white;
}

.navbar {
	margin-bottom: 0;
	background-color: #333;
	z-index: 9999;
	border: 0;
	font-size: 12px !important;
	line-height: 1.42857143 !important;
	letter-spacing: 4px;
	border-radius: 0;
}

.navbar li a, .navbar .navbar-brand {
	color: #fff !important;
}

.navbar-nav li a:hover, .navbar-nav li.active a {
	color: #333 !important;
	background-color:  #f2f2f2 !important;
}

.navbar-default .navbar-toggle {
	border-color: transparent;
	color: #fff !important;
}

.logo {
	color: #333;
	font-size: 150px;
}

tr {
	width: 100%;
	display: inline-table;
	table-layout: fixed;
}
table {
	height: 800px;
}

tbody {
	overflow-y: scroll;
	height: 500px;
	width: 100%;
	position: absolute;
}

</style>
<title>Barry's Board - Employees</title>
</head>
<body style="background-color: #f2f2f2;">
	<%
		ArrayList<CustomerServiceRepresentative> csrs = CustomerServiceRepresentative.getCSRs();
	%>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Barry's Board</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Index.html"><span class="glyphicon glyphicon-home"></span>HOME</a></li>
					<li><a href="Upload.jsp"><span class="glyphicon glyphicon-cloud-upload"></span>UPLOAD FILES</a></li>
					<li><a href="Teams.jsp"><span class="glyphicon glyphicon-book"></span>TEAMS</a></li>
					<li><a href="Employees.jsp"><span class="glyphicon glyphicon-user"></span>EMPLOYEES</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<header>
		<div class="jumbotron">
			<h1><span class="glyphicon glyphicon-user"></span>Employees</h1>
		</div>
	</header>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-2">
				<div class="row">
					<div class="col-lg-12">
						<h3>Search Employees:</h3>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12" style="background-color: #333;">
						<h3 style="color: #fff">Create Employee</h3>
						<input type="text" class="form-control"
							placeholder="Last 4 Digits of Employee No."><br /> <input
							type="text" class="form-control" placeholder="Employee Name"><br />
						<button type="submit" name="btnSubmit">
							<span class="glyphicon glyphicon-print"></span>Add Employee
						</button>
					</div>
				</div>
			</div>
			<div class="col-lg-10">
				<table class="table">
					<thead>
						<tr>
							<th>Employee No.</th>
							<th>Name</th>
							<th>Role</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (CustomerServiceRepresentative csr : csrs) {
						%>
						<tr>
							<td><%=csr.getCsrID()%></td>
							<td><%=csr.getCsrName()%></td>
							<td><%=csr.getEmpType()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>