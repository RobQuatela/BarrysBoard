<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>
<title>Barry's Board - Matching Orders</title>
</head>
<body style="background-color: #f2f2f2;">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Index.html">HOME</a></li>
					<li><a href="Upload.jsp">UPLOAD FILES</a></li>
					<li><a href="Teams.jsp">TEAMS</a></li>
					<li><a href="#pricing">EMPLOYEES</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<header>
		<div class="jumbotron">
			<h1>Barry's Board</h1>
		</div>
	</header>
<body>
<%
	@SuppressWarnings("unchecked")
	HashMap<String, Orders> matchedOrders = (HashMap<String,Orders>)request.getAttribute("matchingOrders");
%>
	<form name="matchedOrders" method="POST" action="InsertMatched">
		<h3>There are <strong><%=matchedOrders.size() %></strong> orders listed below have been matched to previous
		orders in the last 30 days. Do you want to accept these changes?</h3>
		<br />
		<input type="submit" name="btnSubmit" value="Submit Changes">
		<table class="table table-striped">
			<tr>
				<thead>
					<th>Company</th>
					<th>Date Booked</th>
					<th>CSR</th>
					<th>Current Order No.</th>
					<th>Matched Order No.</th>
					<th>Estimator</th>
					<th>Job Type</th>
					<th>Address</th>
				</thead>
			</tr>
			<tbody>
			<% for(Map.Entry<String, Orders> order : matchedOrders.entrySet()) { %>
			<tr>
				<td><%=order.getValue().getCompanyID()%></td>
				<td><%=order.getValue().getDate().getMonthValue()%>/<%=order.getValue().getDate().getDayOfMonth()%>/<%=order.getValue().getDate().getYear()%></td>
				<td><%=CustomerServiceRepresentative.getName(order.getValue().getCsrID())%></td>
				<td><%=order.getValue().getOrderID()%></td>
				<td><%=order.getKey()%></td>
				<td><%=CustomerServiceRepresentative.getName(order.getValue().getCommID())%></td>
				<td><%=order.getValue().getJobType()%></td>
				<td><%=order.getValue().getAddress()%>, <%=order.getValue().getState()%>
					<%=order.getValue().getZip()%></td>
			</tr>
			<% } %>
			</tbody>
		</table>
	</form>

</body>
</html>