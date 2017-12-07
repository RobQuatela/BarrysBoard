<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.barrysboard.model.*" %>
<%@ page import="java.util.*" %>
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
	height: 400px;
}

tbody {
	overflow-y: scroll;
	height: 350px;
	width: 100%;
	position: absolute;
}

.formbox {
	background-color: #f2f2f2;
	color: #333;
}

.formbox-invert {
	background-color: #333;
	color: #f2f2f2;
}

.formbox-invert option {
	color: #333;
}
</style>
<title>Barry's Board</title>
</head>
<body style="background-color: #f2f2f2;">
<%
	int teamNo;
	try {
		teamNo = Integer.parseInt(request.getAttribute("teamNo").toString());
	} catch (Exception e) {
		teamNo = 0;
	}

	ArrayList<Team> teams = Team.getTeams(1);
	ArrayList<CustomerServiceRepresentative> teamLeads = Team.getTeamLeads();
	ArrayList<CustomerServiceRepresentative> csrsElligble = CustomerServiceRepresentative.getCSRsElligble();
	ArrayList<CustomerServiceRepresentative> csrs = CustomerServiceRepresentative.getCSRByTeam(teamNo);
	Team teamDisplay;
	if(teamNo != 0)
		teamDisplay = Team.getTeamById(teamNo);
	else
		teamDisplay = new Team("No Team Selected");
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
			<h1><span class="glyphicon glyphicon-book"></span> Team Management</h1>
		</div>
	</header>
	<div class="row">
			<div class="col-lg-12">
				<form name="frmAddTeam" method="post" action="TeamController"
					class="form-inline">
					<div class="form-group">
						<label for="teamName">Team Name:</label>
						<input type="text" class="form-control" name="teamName" id="teamName"
							placeholder="Enter Team Name">
					</div>
					<div class="form-group">
						<label for="teamLead">Team Lead:</label> <select name="ddlTeamLead"
							id="teamLead" class="form-control">
							<% for (CustomerServiceRepresentative lead : csrsElligble) { %>
								<option value=<%=lead.getCsrID() %>><%=lead.getCsrName() %></option>
							<% } %>
						</select>
					</div>
					<button type="submit" name="btnAddTeam" class="btn btn-default">
						<span class="glyphicon glyphicon-plus"></span> Add Team
					</button>
			</form>
		</div>
	</div>
	<br />
	<div class="row">
	<form name="frmAssignCSR" method="post" action="TeamController">
			<div class="col-lg-4 formbox-invert well">
				<div class="col-lg-6">
				<h3>Teams</h3>
				<select name="lstTeam" multiple="multiple" size="15"
					style="width: auto;">
					<%
						for (Team team : teams) {
					%>
					<option value=<%=team.getTeamID()%>><%=team.getTeamName()%></option>
					<%
						}
					%>
				</select>
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">Status<span class="caret"></span></button>
					<ul class="dropdown-menu">
						<li name="active">Active</li>
					</ul>
				</div>
				</div>
			<div class="col-lg-6">
				<h3>CSRs</h3>
				<select name="lstCsrElligble" multiple="multiple" size="15"
					style="width: auto;">
					<%
						for (CustomerServiceRepresentative csr : csrsElligble) {
					%>
					<option value=<%=csr.getCsrID()%>><%=csr.getCsrName()%></option>
					<%
						}
					%>
				</select>
				<input type="hidden" name="teamId" value=<%=teamDisplay.getTeamID() %>>
				<button type="submit" class="btn btn-default" name="btnAddToTeam">
					<span class="glyphicon glyphicon-plus"></span> Add to Team
				</button>
			</div>
		</div>
			<br />
		<div class="col-lg-8">
		<div class="form-group">
			<button type="submit" class="btn btn-default" name="btnRefresh">
				<span class="glyphicon glyphicon-refresh"></span>
			</button>
			<label for="teamTable"><strong>TEAM:</strong> <%=teamDisplay.getTeamName() %></label>
		</div>
		<table class="table">
		<thead>
			<tr>
				
				<th>ID</th>
				<th>Name</th>
				<th>Team</th>
				<th></th>
			</tr>
		</thead>
			<tbody>
			<% for(CustomerServiceRepresentative csr: csrs) { %>
				<tr>
					<td><%=csr.getCsrID() %></td>
					<td><%=csr.getCsrName() %></td>
					<td><%=csr.getTeamName() %></td>
					<td><button type="submit" class="btn" name="btnDel" value=<%="btnDel" + csr.getCsrID() %>>
						<span class="glyphicon glyphicon-remove"></span>
					</button></td>
				</tr>
			<% } %>
			</tbody>
		</table>
		</div>
	</form>
	</div>
</body>
</html>