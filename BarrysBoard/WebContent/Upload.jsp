<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="css/Main.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Barry's Board</title>
</head>
<body onload="myFunction()">
	<div align="center">
		<h1>Barry's Board</h1>
		<a href="Index.html"><img src="images/dry-erase-eraser-cartoon-clipart-1.jpg" align="middle" alt="Barry's Board"/></a>
		<ul>
			<li><a href="Index.html">Home</a></li>
			<li><a class="active" href="Upload.jsp">Upload Files</a></li>
		</ul>
	</div>
	<form name="insertOrders" method="POST" action="Insert">
	<h3>Which report type would you like to upload?</h3>
	<p>Report Type: 
		<select name="reportType">
			<option value="phone activity">Phone Activity Report</option>
			<option value="scheduled jobs">Scheduled Jobs by Date</option>
		</select>
	</p>
	<input type="file" id="myFile" name="phoneFile" multiple size="50" onchange="myFunction()">
	<p id="demo"></p>
	<script>
		function myFunction() {
			var x = document.getElementById("myFile");
			var txt = "";
			var path = "";
			if('files' in x) {
				if(x.files.length == 0) {
					txt = "Select a file.";
				} else {
					for (var i = 0; i < x.files.length; i++) {
						//txt += "<br><strong>" + file.name + "</strong><br>";
						var file = x.files[i];
						if('name' in file) {
							txt += "<br><strong>" + file.name + "</strong><br>";
							path += "C:/Users/rquatela/Desktop/" + file.name;
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
	<p>File Path: <input type="text" name="filePath" id="filePath" style="width:400px;"></p>
	<input type="submit" name="btnSubmit" value="Submit">
	</form>
</body>
</html>