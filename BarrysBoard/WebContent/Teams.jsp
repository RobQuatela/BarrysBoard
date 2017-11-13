<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.barrysboard.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/Main.css" />
<link rel="icon" type="image/gif" href="images/barrysboard.ico">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Barry's Board</title>
</head>
<body>
	<div align="center">
		<h1>Barry's Board</h1>
		<a href="Index.html"><img src="images/dry-erase-eraser-cartoon-clipart-1.jpg" align="middle" alt="Barry's Board"/></a>
		<ul>
			<li><a class="active" href="Index.html">Home</a></li>
			<li><a href="Upload.jsp">Upload Files</a></li>
			<li><a href="Teams.jsp">Teams</a></li>
		</ul>
	</div>
	<div id="homepage">
	<br />
	<h2>Teams</h2>
	<form name="update" method="POST" action="Update">
		<select name="teams" multiple="multiple" size="10">
			<% 
				ArrayList<Team> teams = Team.getTeams(1); 
			%>
			<% for(Team team : teams) {%>
				<option value=<%= team.getTeamID() %> ><%= team.getTeamName() %></option>
			<% } %>
		</select>
		<select name="csrteam" multiple="multiple" size="10">
			<%
				ArrayList<CustomerServiceRepresentative> csrs = CustomerServiceRepresentative.getCSRByTeam(1);
			%>
			<% for(CustomerServiceRepresentative csr : csrs) { %>
				<option value=<%=csr.getCsrID() %> ><%=csr.getCsrName() %></option>
			<% } %>
		</select>
	</form>
	</div>
</body>
</html>