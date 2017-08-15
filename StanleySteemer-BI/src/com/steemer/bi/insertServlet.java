package com.steemer.bi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		String company = request.getParameter("company");
		int year = Integer.parseInt(request.getParameter("year"));
		String month = request.getParameter("month");
		int week = Integer.parseInt(request.getParameter("week"));
		String[] dayOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		ArrayList<Calls> callList = new ArrayList<>();
		ArrayList<Sales> salesList = new ArrayList<>();
		ArrayList<Jobs> jobsList = new ArrayList<>();

		for(int i = 0; i < dayOfWeek.length; i++) {
			try {
				String dataDay = "" + String.valueOf(i+1) + ":" + dayOfWeek[i].toString() + "";
				callList.add(new Calls(company, year, month, week, dataDay,
						Integer.parseInt(request.getParameter("call orders:" + dayOfWeek[i])),
						Integer.parseInt(request.getParameter("web orders:" + dayOfWeek[i])),
						Integer.parseInt(request.getParameter("booked orders:" + dayOfWeek[i])),
						Integer.parseInt(request.getParameter("loss orders:" + dayOfWeek[i])),
						Integer.parseInt(request.getParameter("estimate orders:" + dayOfWeek[i]))));
				salesList.add(new Sales(company, year, month, week, dataDay,
						Integer.parseInt(request.getParameter("core:" + dayOfWeek[i])),
						Integer.parseInt(request.getParameter("duct:" + dayOfWeek[i]))));
				jobsList.add(new Jobs(company, year, month, week, dataDay,
						Integer.parseInt(request.getParameter("corejobs:" + dayOfWeek[i])),
						Integer.parseInt(request.getParameter("ductjobs:" + dayOfWeek[i]))));
			}
			catch(NumberFormatException e) {
				
			}
		}
		
		for(Calls call : callList)
			call.insert();
		
		for(Sales sales : salesList)
			sales.insert();
		
		for(Jobs jobs : jobsList)
			jobs.insert();

		response.sendRedirect("http://localhost:8080/StanleySteemer-BI/index.html");

	}
	

}
