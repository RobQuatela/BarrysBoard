<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
</style>
<title>Barry's Board</title>
</head>
<body onload="myFunction()" style="background-color: #f2f2f2;">
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
			<h1><span class="glyphicon glyphicon-cloud-upload"></span> File Management</h1>
		</div>
	</header>
	<div class="row">
	<div class="col-lg-6">
	<form name="insertOrders" method="POST" action="Insert" enctype="multipart/form-data">
	<h3>Which report type would you like to upload?</h3>
	<label for="reports">Report Type:</label>
		<select class="form-control" id="reports" name="reportType">
			<option value="booked">Jobs by Booked Date</option>
			<option value="scheduled">Jobs by Scheduled Date</option>
			<option value="loss">Lost Calls</option>
			<option value="incomplete">Daily Calls Received</option>
			<option value="commSale">Commercial Paid/Unpaid</option>
			<option value="employee">Employee Master List</option>
		</select>
	<br />
	<input type="file" id="myFile" name="uploadFile" multiple="true" onchange="uploadFile()" >
	<p id="demo"></p>
	<script>
		function uploadFile() {
			var x = document.getElementById("myFile");
			var txt = "";
			var path = "";
			if('files' in x) {
				if(x.files.length == 0) {
					txt = "Select a file.";
				} else {
					for (var i = 0; i < x.files.length; i++) {
						var file = x.files[i];
						if('name' in file) {
							txt += "<br><strong>" + file.name + "</strong><br>";
							path += file.name;
						}				
					}
				}
			} else {
				if(x.value == "") {
					txt += "Select one or more files.";
				} else {
					txt += "The files property is not supported by your browser!";
					txt += "<br>The path of the selected file: " + x.value;
				}
			}
			document.getElementById("demo").innerHTML = txt;
			document.getElementById("filePath").value = path;
		}
	</script>
	<br />
	<!-- <p>File: <input type="text" name="filePath" id="filePath" style="width:400px;"></p>-->
	<!-- <span class="glyphicon glyphicon-cloud-upload"><input type="submit" name="btnSubmit" value="Submit"></span>-->
	<button type="submit" class="btn-default">
		<span class="glyphicon glyphicon-cloud-upload"></span> Upload
	</button>
	</form>
	</div>
	</div>
</body>
</html>