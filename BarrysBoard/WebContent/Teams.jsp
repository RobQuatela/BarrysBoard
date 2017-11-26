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
<style>
	table {
		font-family: arial, sans-serif;
		border-collapse: collapse;
		width: 80%;
	}
	td, th {
		border: 1px solid #dddddd;
		text-align: left;
		padding: 8px;
	}
	tr:nth-child(even) {
		background-color: #dddddd;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Barry's Board</title>
</head>
<body>
	<div align="center">
		<h1>Barry's Board</h1>
		<a href="Index.html"><img src="images/dry-erase-eraser-cartoon-clipart-1.jpg" align="middle" alt="Barry's Board"/></a>
		<ul>
			<li><a href="Index.html">Home</a></li>
			<li><a href="Upload.jsp">Upload Files</a></li>
			<li><a class="active" href="Teams.jsp">Teams</a></li>
		</ul>
	</div>
	<div id="homepage">
	<br />
	<h2>Teams</h2>
	<form name="update" method="POST" action="Update">
		<select name="teams" multiple="multiple" size="10" style="width:auto;">
			<% 
				int teamNo;
				try {
					teamNo = (int)request.getAttribute("team");
				} catch(Exception e) {
					teamNo = 0;
				}
				
				ArrayList<Team> teams = Team.getTeams(1); 
				ArrayList<CustomerServiceRepresentative> csrs = CustomerServiceRepresentative.getCSRByTeam(teamNo);
			%>
			<% for(Team team : teams) {%>
				<option value=<%= team.getTeamID() %> ><%= team.getTeamName() %></option>
			<% } %>
		</select>
		<br />
		<% if(teamNo == 0) { %>
			<input type="checkbox" name="listView" value="All" checked>All
		<% } else { %>
			<input type="checkbox" name="listView" value="All">All
		<% } %>
		<br />
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Team</th>
			</tr>
			<% for(CustomerServiceRepresentative csr: csrs) { %>
				<tr>
					<td><%=csr.getCsrID() %></td>
					<td><%=csr.getCsrName() %></td>
					<td><%=csr.getTeamName() %></td>
				</tr>
			<% } %>
		</table>
	</form>
	</div>
</body>
</html>