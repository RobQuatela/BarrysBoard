package com.steemer.bi;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int company = Integer.parseInt(request.getParameter("hiddenCompany"));
		int year = Integer.parseInt(request.getParameter("hiddenYear"));
		int month = Integer.parseInt(request.getParameter("hiddenMonth"));
		int week = Integer.parseInt(request.getParameter("hiddenWeek"));
		int day = Integer.parseInt(request.getParameter("hiddenDay"));
		LocalDate date = LocalDate.parse(request.getParameter("sales date"));
		int salesTotal = Integer.parseInt(request.getParameter("total sales"));
		int salesAirduct = Integer.parseInt(request.getParameter("duct sales"));
		int jobsTotal = Integer.parseInt(request.getParameter("total jobs"));
		int jobsDuct = Integer.parseInt(request.getParameter("duct jobs"));
		int ordersCalls = Integer.parseInt(request.getParameter("call orders"));
		int ordersOnline = Integer.parseInt(request.getParameter("web orders"));
		int ordersBooked = Integer.parseInt(request.getParameter("booked orders"));
		int ordersLost = Integer.parseInt(request.getParameter("loss orders"));
		int ordersEstimates = Integer.parseInt(request.getParameter("estimate orders"));
		
		Sales sale = new Sales(company, year, month, week, day, date, salesTotal, salesAirduct);
		Jobs job = new Jobs(company, year, month, week, day, date, jobsTotal, jobsDuct);
		Calls call = new Calls(company, year, month, week, day, date, ordersCalls, ordersOnline,
				ordersBooked, ordersLost, ordersEstimates);
		
		sale.updateSales();
		job.updateJobs();
		call.updateCalls();
		
		request.setAttribute("company", company);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("week", week);
		request.setAttribute("day", day);
		request.getRequestDispatcher("Update.jsp").forward(request, response);
	}

}
