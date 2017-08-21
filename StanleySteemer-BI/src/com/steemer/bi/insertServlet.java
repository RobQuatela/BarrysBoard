package com.steemer.bi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertServlet
 */
@WebServlet("/insertServlet")
public class insertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public insertServlet() {
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
		int company = Integer.parseInt(request.getParameter("company"));
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int week = Integer.parseInt(request.getParameter("week"));
		ArrayList<Calls> callList = new ArrayList<>();
		ArrayList<Sales> salesList = new ArrayList<>();
		ArrayList<Jobs> jobsList = new ArrayList<>();
		ArrayList<Day> days = Day.getDays();

		for(Day day : days) {
			try {
				callList.add(new Calls(company, year, month, week, day.getDayID(),
						LocalDate.parse(request.getParameter("ordersdate:" + day.getDayName())),
						Integer.parseInt(request.getParameter("call orders:" + day.getDayName())),
						Integer.parseInt(request.getParameter("web orders:" + day.getDayName())),
						Integer.parseInt(request.getParameter("booked orders:" + day.getDayName())),
						Integer.parseInt(request.getParameter("loss orders:" + day.getDayName())),
						Integer.parseInt(request.getParameter("estimate orders:" + day.getDayName()))));
			} catch(NumberFormatException | DateTimeParseException e) {
				
			}
			
			try {
				salesList.add(new Sales(company, year, month, week, day.getDayID(),
						LocalDate.parse(request.getParameter("salesdate:" + day.getDayName())),
						Integer.parseInt(request.getParameter("total:" + day.getDayName())),
						Integer.parseInt(request.getParameter("duct:" + day.getDayName()))));	
			} catch(NumberFormatException | DateTimeParseException e) {
				
			}
			
			try {
				jobsList.add(new Jobs(company, year, month, week, day.getDayID(),
						LocalDate.parse(request.getParameter("salesdate:" + day.getDayName())),
						Integer.parseInt(request.getParameter("totaljobs:" + day.getDayName())),
						Integer.parseInt(request.getParameter("ductjobs:" + day.getDayName()))));
			} catch(NumberFormatException | DateTimeParseException e) {
				
			}
		}
		
		if(!callList.isEmpty())
			for(Calls call : callList)
				call.insert();
		
		if(!salesList.isEmpty())
			for(Sales sales : salesList)
				sales.insert();
		
		if(!jobsList.isEmpty())
			for(Jobs jobs : jobsList)
				jobs.insert();

		
		response.sendRedirect("Insert.jsp");

	}
	

}
