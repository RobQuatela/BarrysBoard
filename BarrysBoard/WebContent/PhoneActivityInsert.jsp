<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="myFunction()">
	<form name="insertOrders" method="POST" action="OrdersInsert">
	<input type="file" id="myFile" name="phoneFile" multiple size="50" onchange="myFunction()">
	<p id="demo"></p>
	<script>
		function myFunction() {
			var x = document.getElementById("myFile");
			var txt = "";
			var path = "";
			if('files' in x) {
				if(x.files.length == 0) {
					txt = "Select one or more files.";
				} else {
					for (var i = 0; i < x.files.length; i++) {
						//txt += "<br><strong>" + file.name + "</strong><br>";
						var file = x.files[i];
						if('name' in file) {
							txt += "<br><strong>" + file.name + "</strong><br>";
							txt += "Path: C:/Users/rquatela/Desktop/" + file.name + "<br>";
							path += "Z:/" + file.name + "<br>";
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
	<input type="text" name="filePath" id="filePath">
	<input type="submit" name="btnSubmit" value="Submit">
	</form>
</body>
</html>