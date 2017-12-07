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
	height: 600px;
}

tbody {
	overflow-y: scroll;
	height: 550px;
	width: 100%;
	position: absolute;
}

</style>
<script>
	function ifChecked() {
		if (document.getElementById("ckID").checked === true) {
			alert("Im checked!");
		}
	}
	
	function toggleEdit() {
		if(document.getElementById("ckID").checked == true) {
			
		}
	}
</script>
<title>Barry's Board - Employees</title>
</head>
<body style="background-color: #f2f2f2;">
	<%
		ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
		if(request.getAttribute("csrs") == null)
			csrs = CustomerServiceRepresentative.getCSRs();
		else
			csrs = (ArrayList<CustomerServiceRepresentative>) session.getAttribute("csrs");

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
			<h1><span class="glyphicon glyphicon-user"></span> Employee Management</h1>
		</div>
	</header>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-3">
				<div class="row">
					<form name="frmEmployeeSearch" method="get" action="EmployeeInsert">
						<div class="col-lg-12 well">
							<h3>Search Employees</h3>
							<div class="form-group">
								<label for="search">Name/Employee No:</label>
								<input type="text" class="form-control" name="empNameNo" id="search" placeholder="Search"><br />
								<label for="status">Status:</label>
								<select name="empStatus" id="status" class="form-control">
									<option value="A">A</option>
									<option value="I">I</option>
									<option value="L">L</option>
								</select><br />
								<label for="role">Role:</label>
								<select name="empRole" id="role" class="form-control">
									<option value="csr">CSR</option>
									<option value="esr">Estimator</option>
									<option value="other">Other</option>
								</select><br />
								<button type="submit" class="btn btn-default" name="btnSearchEmp">
									<span class="glyphicon glyphicon-search"></span> Search
								</button>
							</div>
						</div>
					</form>
				</div>
				<div class="row">
					<form name="frmEmployeeInsert" method="post"
						action="EmployeeInsert">
						<div class="col-lg-12 well" style="background-color: #333;">
							<h3 style="color: #fff">Create Employee</h3>
							<div class="form-group">
								<input type="text" class="form-control" name="empID"
									placeholder="Last 4 Digits of Employee No."><br /> <input
									type="text" class="form-control" name="empName"
									placeholder="Employee Name"><br /> <label for="role"
									style="color: #fff">Role:</label> <select id="role"
									name="empRole" class="form-control">
									<option value="CSR">CSR</option>
									<option value="ESR">Estimator</option>
									<option value="neither">Neither</option>
								</select><br />
								<button type="submit" class="btn btn-default" name="btnInsertEmp">
									<span class="glyphicon glyphicon-plus"></span> Add Employee
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<form name="frmUpdateEmployee" method="post" action="EmployeeInsert" class="form-group">
				<div class="col-lg-9">
					<button type="submit" class="btn btn-default" name="btnUpdateEmp">
						<span class="glyphicon glyphicon-floppy-saved"></span> Update Selected
					</button><br />
					<table class="table table-bordered" id="searchTable">
						<thead>
							<tr>
								<th>Employee No.</th>
								<th>Name</th>
								<th>Role</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (CustomerServiceRepresentative csr : csrs) {
							%>
							<tr>
								<td><input type="checkbox" name="ckID" id="ckID" onchange=ifChecked()
									value=<%=csr.getCsrID()%>><%=csr.getCsrID()%></td>
								<td><input type="text" contentEditable="false" name=<%="txtName" + csr.getCsrID()%>
									value="<%=csr.getCsrName()%>"></td>
								<td><input type="text" name=<%="txtRole" + csr.getCsrID()%>
									value=<%=csr.getEmpType()%>></td>
								<td><input type="text" name=<%="txtStatus" + csr.getCsrID()%>
									value=<%=csr.getCsrActive()%>></td>
							</tr>
							<%
							}
						%>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>