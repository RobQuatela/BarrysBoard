package com.steemer.bi;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
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
		// TODO Auto-generated method stub
		String company = request.getParameter("company");
		int year = Integer.parseInt(request.getParameter("year"));
		String month = request.getParameter("month");
		int week = Integer.parseInt(request.getParameter("week"));
		double monday, tuesday, wednesday, thursday, friday, saturday, sunday;
		Connection con = null;
		String connString = "jdbc:mysql://localhost:3306/dbsteemerbi?autoReconnect=true&useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		PreparedStatement ps = null;
		String accessDriver = "net.ucanaccess.jdbc.UcanaccessDriver";
		String accessConString = "jdbc:ucanaccess://C:/Users/rquatela/SharePoint/Quatela Group - Documents/Quatela Group/Data-BI-Quatela.accdb";
		
		try { monday = Double.parseDouble(request.getParameter("core:Monday")); } catch(NumberFormatException e) { monday = 0; }
		try { tuesday = Double.parseDouble(request.getParameter("core:Tuesday")); } catch(NumberFormatException e) { tuesday = 0; }
		try { wednesday = Double.parseDouble(request.getParameter("core:Wednesday")); } catch(NumberFormatException e) { wednesday = 0; }
		try { thursday = Double.parseDouble(request.getParameter("core:Thursday")); } catch(NumberFormatException e) { thursday = 0; }
		try { friday = Double.parseDouble(request.getParameter("core:Friday")); } catch(NumberFormatException e) { friday = 0; }
		try { saturday = Double.parseDouble(request.getParameter("core:Saturday")); } catch(NumberFormatException e) { saturday = 0; }
		try { sunday = Double.parseDouble(request.getParameter("core:Sunday")); } catch(NumberFormatException e) { sunday = 0; }
		
		HashMap<String, Double> days = new HashMap<>();
		days.put("1:Monday", monday);
		days.put("2:Tuesday", tuesday);
		days.put("3:Wednesday", wednesday);
		days.put("4:Thursday", thursday);
		days.put("5:Friday", friday);
		days.put("6:Saturday", saturday);
		days.put("7:Sunday", sunday);
		

		try {
			//Class.forName(driver).newInstance();
			//con = DriverManager.getConnection(connString, "root", "P@ssG0!");
			Class.forName(accessDriver);
			con = DriverManager.getConnection(accessConString);
			ps = con.prepareStatement("INSERT INTO tbsales (company, sales_year, sales_month, sales_week, sales_day, sales_core, sales_airduct) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?)");
			for(Map.Entry<String, Double> entry : days.entrySet()) {
				if(entry.getValue() != 0) {
					ps.setString(1, company);
					ps.setInt(2, year);
					ps.setString(3, month);
					ps.setInt(4, week);
					ps.setString(5, entry.getKey());
					ps.setDouble(6, entry.getValue());
					ps.setDouble(7, 0);
					ps.executeUpdate();
				}
				
			}
			//response.getWriter().println("<h3>You did it!!</h3>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("http://localhost:8080/StanleySteemer-BI/index.html");
/*		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>This is a test</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h3>Hello there!" + day + "</h3>");
		writer.println("</body>");
		writer.println("</html>");*/
	}

}
