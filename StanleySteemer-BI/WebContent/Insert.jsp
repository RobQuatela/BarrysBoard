<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.steemer.bi.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Barry's Board - Insert Records</title>
</head>
<body>
<%
	ArrayList<Company> companies = Company.getCompany();
	ArrayList<Year> years = Year.getYears();
	ArrayList<Month> months = Month.getMonths();
	ArrayList<Week> weeks = Week.getWeeks();
	ArrayList<Day> days = Day.getDays();
%>
<ul>
	<li><a href="Insert.jsp">Insert Records</a></li>
	<li><a href="Update.jsp">Update Records</a></li>
</ul>
<form name="inputForm" method="POST" action="insertServlet">
	<h3>Company:</h3>
		<p><select name="company">
			<% for(Company company : companies) { %>
				<option value=<%= company.getCompanyID() %>><%= company.getCompanyName() %></option>
			<% } %>
		</select>
		</p>
	<h3>Year:</h3> 
		<p><select name="year">
			<% for(Year year : years) { %>
				<% if(year.getYearID() == 2017) { %>
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
				<% if(month.getMonthID() == 8) { %>
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
			<input type="radio" name="week" value=<%= week.getWeekID() %>><%= week.getWeekID() %> &nbsp
		<% } %>
		</p>

	<h3>Data:</h3>
	<table>
		<tr>
			<td><h4>Day</h4></td>
			<td><h4>Date</h4></td>
			<td><h4>Total Sales</h4></td>
			<td><h4>Airduct Sales</h4></td>
			<td><h4>Total Jobs</h4></td>
			<td><h4>Airduct Jobs</h4></td>
		</tr>
		<tr>
			<td>Monday</td>
			<td><input type="date" name="salesdate:1:Monday"></td>
			<td><input type="text" name="total:1:Monday"/></td>
			<td><input type="text" name="duct:1:Monday"/></td>
			<td><input type="text" name="totaljobs:1:Monday"/></td>
			<td><input type="text" name="ductjobs:1:Monday"/></td>
		</tr>
		<tr>
			<td>Tuesday</td>
			<td><input type="date" name="salesdate:2:Tuesday"></td>
			<td><input type="text" name="total:2:Tuesday"/></td>
			<td><input type="text" name="duct:2:Tuesday"/></td>
			<td><input type="text" name="totaljobs:2:Tuesday"/></td>
			<td><input type="text" name="ductjobs:2:Tuesday"/></td>
		</tr>
		<tr>
			<td>Wednesday</td>
			<td><input type="date" name="salesdate:3:Wednesday"></td>
			<td><input type="text" name="total:3:Wednesday"/></td>
			<td><input type="text" name="duct:3:Wednesday"/></td>
			<td><input type="text" name="totaljobs:3:Wednesday"/></td>
			<td><input type="text" name="ductjobs:3:Wednesday"/></td>
		</tr>
		<tr>
			<td>Thursday</td>
			<td><input type="date" name="salesdate:4:Thursday"></td>
			<td><input type="text" name="total:4:Thursday"/></td>
			<td><input type="text" name="duct:4:Thursday"/></td>
			<td><input type="text" name="totaljobs:4:Thursday"/></td>
			<td><input type="text" name="ductjobs:4:Thursday"/></td>
		</tr>
		<tr>
			<td>Friday</td>
			<td><input type="date" name="salesdate:5:Friday"></td>
			<td><input type="text" name="total:5:Friday"/></td>
			<td><input type="text" name="duct:5:Friday"/></td>
			<td><input type="text" name="totaljobs:5:Friday"/></td>
			<td><input type="text" name="ductjobs:5:Friday"/></td>
		</tr>
		<tr>
			<td>Saturday</td>
			<td><input type="date" name="salesdate:6:Saturday"></td>
			<td><input type="text" name="total:6:Saturday"/></td>
			<td><input type="text" name="duct:6:Saturday"/></td>
			<td><input type="text" name="totaljobs:6:Saturday"/></td>
			<td><input type="text" name="ductjobs:6:Saturday"/></td>
		</tr>
		<tr>
			<td>Sunday</td>
			<td><input type="date" name="salesdate:7:Sunday"></td>
			<td><input type="text" name="total:7:Sunday"/></td>
			<td><input type="text" name="duct:7:Sunday"/></td>
			<td><input type="text" name="totaljobs:7:Sunday"/></td>
			<td><input type="text" name="ductjobs:7:Sunday"/></td>
		</tr>
			
	</table>
	
	<h3>Calls</h3>
		<table>
		<tr>
			<td><h4>Day</h4></td>
			<td><h4>Date</h4></td>
			<td><h4>Call Orders</h4></td>
			<td><h4>Web Orders</h4></td>
			<td><h4>Booked Orders</h4></td>
			<td><h4>Lost Orders</h4></td>
			<td><h4>Estimated Orders</h4></td>
		</tr>
		<tr>
			<td>Monday</td>
			<td><input type="date" name="ordersdate:1:Monday"></td>
			<td><input type="text" name="call orders:1:Monday"/></td>
			<td><input type="text" name="web orders:1:Monday"/></td>
			<td><input type="text" name="booked orders:1:Monday"/></td>
			<td><input type="text" name="loss orders:1:Monday"/></td>
			<td><input type="text" name="estimate orders:1:Monday"/></td>
		</tr>
		<tr>
			<td>Tuesday</td>
			<td><input type="date" name="ordersdate:2:Tuesday"></td>
			<td><input type="text" name="call orders:2:Tuesday"/></td>
			<td><input type="text" name="web orders:2:Tuesday"/></td>
			<td><input type="text" name="booked orders:2:Tuesday"/></td>
			<td><input type="text" name="loss orders:2:Tuesday"/></td>
			<td><input type="text" name="estimate orders:2:Tuesday"/></td>
		</tr>
		<tr>
			<td>Wednesday</td>
			<td><input type="date" name="ordersdate:3:Wednesday"></td>
			<td><input type="text" name="call orders:3:Wednesday"/></td>
			<td><input type="text" name="web orders:3:Wednesday"/></td>
			<td><input type="text" name="booked orders:3:Wednesday"/></td>
			<td><input type="text" name="loss orders:3:Wednesday"/></td>
			<td><input type="text" name="estimate orders:3:Wednesday"/></td>
		</tr>
		<tr>
			<td>Thursday</td>
			<td><input type="date" name="ordersdate:4:Thursday"></td>
			<td><input type="text" name="call orders:4:Thursday"/></td>
			<td><input type="text" name="web orders:4:Thursday"/></td>
			<td><input type="text" name="booked orders:4:Thursday"/></td>
			<td><input type="text" name="loss orders:4:Thursday"/></td>
			<td><input type="text" name="estimate orders:4:Thursday"/></td>
		</tr>
		<tr>
			<td>Friday</td>
			<td><input type="date" name="ordersdate:5:Friday"></td>
			<td><input type="text" name="call orders:5:Friday"/></td>
			<td><input type="text" name="web orders:5:Friday"/></td>
			<td><input type="text" name="booked orders:5:Friday"/></td>
			<td><input type="text" name="loss orders:5:Friday"/></td>
			<td><input type="text" name="estimate orders:5:Friday"/></td>
		</tr>
		<tr>
			<td>Saturday</td>
			<td><input type="date" name="ordersdate:6:Saturday"></td>
			<td><input type="text" name="call orders:6:Saturday"/></td>
			<td><input type="text" name="web orders:6:Saturday"/></td>
			<td><input type="text" name="booked orders:6:Saturday"/></td>
			<td><input type="text" name="loss orders:6:Saturday"/></td>
			<td><input type="text" name="estimate orders:6:Saturday"/></td>
		</tr>
		<tr>
			<td>Sunday</td>
			<td><input type="date" name="ordersdate:7:Sunday"></td>
			<td><input type="text" name="call orders:7:Sunday"/></td>
			<td><input type="text" name="web orders:7:Sunday"/></td>
			<td><input type="text" name="booked orders:7:Sunday"/></td>
			<td><input type="text" name="loss orders:7:Sunday"/></td>
			<td><input type="text" name="estimate orders:7:Sunday"/></td>
		</tr>

	</table>

	<p>
		<input type="submit" value="Submit" name="btnSubmit" /> &nbsp
		<input type="reset" value="Clear" name="btnClear" />
	</p>
</form>
</body>
</html>