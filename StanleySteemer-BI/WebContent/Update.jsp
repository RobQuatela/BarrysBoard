<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.steemer.bi.*" %>
<%@ page import="java.util.*" %>    
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Barry's Board - Update Records</title>
</head>
<body>
<%
	ArrayList<Company> companies = Company.getCompany();
	ArrayList<Year> years = Year.getYears();
	ArrayList<Month> months = Month.getMonths();
	ArrayList<Week> weeks = Week.getWeeks();
	ArrayList<Day> days = Day.getDays();
	
	int companyAtt = 1, yearAtt = 2017, monthAtt = 8, weekAtt = 1, dayAtt = 1;
	try {		
 		companyAtt = Integer.parseInt(request.getParameter("company"));
		yearAtt = Integer.parseInt(request.getParameter("year"));
		monthAtt = Integer.parseInt(request.getParameter("month"));
		weekAtt = Integer.parseInt(request.getParameter("week"));
		dayAtt = Integer.parseInt(request.getParameter("day"));
/* 		companyAtt = (int)request.getAttribute("company");
		yearAtt = (int)request.getAttribute("year");
		monthAtt = (int)request.getAttribute("month");
		weekAtt = (int)request.getAttribute("week");
		dayAtt = (int)request.getAttribute("day"); */
	} catch (Exception e) {

	}

	Sales sale = new Sales(companyAtt, yearAtt, monthAtt, weekAtt, dayAtt);
	Jobs job = new Jobs(companyAtt, yearAtt, monthAtt, weekAtt, dayAtt);
	Calls call = new Calls(companyAtt, yearAtt, monthAtt, weekAtt, dayAtt);
	Sales updateSales = null;
	Jobs updateJobs = null;
	Calls updateCalls = null;
	
	try {
		updateSales = sale.getSales();
		updateJobs = job.getJob();
		updateCalls = call.getCall();
	} catch(Exception e) {
		
	}
	
	request.setAttribute("company", companyAtt);
	request.setAttribute("year", yearAtt);
	request.setAttribute("month", monthAtt);
	request.setAttribute("week", weekAtt);
	request.setAttribute("day", dayAtt);
%>


<ul>
	<li><a href="Insert.jsp">Insert Records</a></li>
	<li><a href="Update.jsp">Update Records</a></li>
</ul>

<form name="updatePage" method="GET">
	<h3>Company:</h3>
		<p><select name="company" id="company" >
			<% for(Company company : companies) { %>
				<% if(company.getCompanyID() == (int)request.getAttribute("company")) { %>
					<option value=<%= company.getCompanyID() %> selected ><%= company.getCompanyName() %></option>
				<% } else { %>
					<option value=<%= company.getCompanyID() %> ><%= company.getCompanyName() %></option>
				<% } %>
			<% } %>
		</select>
		</p>
	<h3>Year:</h3> 
		<p><select name="year">
			<% for(Year year : years) { %>
				<% if(year.getYearID() == (int)request.getAttribute("year")) { %>
					<option value=<%= year.getYearID() %> selected ><%= year.getYearID() %></option>
				<% } else { %>
					<option value=<%= year.getYearID() %> ><%= year.getYearID() %></option>
				<% } %>
			<% } %>
		</select>
		</p>
	<h3>Month:</h3>
		<p><select name="month">
			<% for(Month month : months) { %>
				<% if(month.getMonthID() == (int)request.getAttribute("month")) { %>
					<option value=<%= month.getMonthID() %> selected ><%= month.getMonthName() %></option>
				<% } else { %>
					<option value=<%= month.getMonthID() %> ><%= month.getMonthName() %></option>
				<% } %>
			<% } %>
		</select>
		</p>
	<h3>Week:</h3>
		<p>
		<% for(Week week : weeks) { %>
			<% if(week.getWeekID() == (int)request.getAttribute("week")) { %>
				<input type="radio" name="week" value=<%= week.getWeekID() %> checked="checked"><%= week.getWeekID() %> &nbsp
			<% } else { %>
				<input type="radio" name="week" value=<%= week.getWeekID() %>><%= week.getWeekID() %> &nbsp
			<% } %>
		<% } %>
		</p>

	<h3>Data:</h3>
	<table>
		<tr>
			<td><input type="submit" value="Refresh" name="btnSubmit" /></td>
		</tr>
		<tr>
			<td>
				<select name="day" multiple="multiple" size="7">
				<% for (Day day : days) { %>
					<% if(day.getDayID() == (int)request.getAttribute("day")) { %>
						<option value=<%= day.getDayID() %> selected ><%= day.getDayName() %></option>
					<% } else { %>
						<option value=<%= day.getDayID() %> ><%= day.getDayName() %></option>
					<% } %>
				<% } %>
				</select>
			</td>
			<td>
</form>
<form name="updateForm" method="POST" action="UpdateServlet">
	<input type="hidden" name="hiddenCompany" value=<%= request.getParameter("company") %>>
	<input type="hidden" name="hiddenYear" value=<%= request.getParameter("year") %>>
	<input type="hidden" name="hiddenMonth" value=<%= request.getParameter("month") %>>
	<input type="hidden" name="hiddenWeek" value=<%= request.getParameter("week") %>>
	<input type="hidden" name="hiddenDay" value=<%= request.getParameter("day") %>>
				<table>
					<tr>
						<td><h4>Date</h4></td>
						<td><h4>Total Sales</h4></td>
						<td><h4>Airduct Sales</h4></td>
						<td><h4>Total Jobs</h4></td>
						<td><h4>Airduct Jobs</h4></td>
					</tr>
					<tr>
						<td><input type="date" name="sales date" value=<%= updateSales.getDate() %>></td>
						<td><input type="text" name="total sales" value=<%= updateSales.getTotalSales() %>></td>
						<td><input type="text" name="duct sales" value=<%= updateSales.getSalesAirduct() %>></td>
						<td><input type="text" name="total jobs" value=<%= updateJobs.getJobsTotal() %>></td>
						<td><input type="text" name="duct jobs" value=<%= updateJobs.getJobsAirduct() %>></td>
					</tr>
					<tr>
						<td><h4>Call Orders</h4></td>
						<td><h4>Web Orders</h4></td>
						<td><h4>Booked Orders</h4></td>
						<td><h4>Lost Orders</h4></td>
						<td><h4>Estimated Orders</h4></td>
					</tr>
					<tr>
						<td><input type="text" name="call orders" value=<%= updateCalls.getCallOrders() %>></td>
						<td><input type="text" name="web orders" value=<%= updateCalls.getOnlineOrders() %>></td>
						<td><input type="text" name="booked orders" value=<%= updateCalls.getBookedOrders() %>></td>
						<td><input type="text" name="loss orders" value=<%= updateCalls.getLossOrders() %>></td>
						<td><input type="text" name="estimate orders" value=<%= updateCalls.getEstimateOrders() %>></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<p>
		<input type="submit" value="Submit" name="btnSubmit" />
	</p>
</form>
</body>
</html>