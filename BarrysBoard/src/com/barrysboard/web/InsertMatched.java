package com.barrysboard.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barrysboard.model.Orders;
import com.barrysboard.service.OrdersService;

/**
 * Servlet implementation class InsertMatched
 */
@WebServlet("/InsertMatched")
public class InsertMatched extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertMatched() {
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
		
		@SuppressWarnings("unchecked")
		//ArrayList<Orders> matchedOrders = (ArrayList<Orders>)request.getSession().getAttribute("matchedOrders");
		HashMap<String, Orders> matchedOrders = (HashMap<String, Orders>)request.getSession().getAttribute("matchedOrders");
		
		OrdersService.readMatchedOrders(matchedOrders);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("table { font-family: arial, sans-serif; border-collapse: collapse; width: 100%; }");
		out.println("td, th { border: 1px solid #dddddd); text-align: left; padding: 8px; }");
		out.println("tr:nth-child(even) { background-color: #dddddd; }");
		out.println("</style>");
		out.println("<meta charset='ISO-8859-1'>");
		out.println("<link rel='stylesheet' href='css/Main.css' />");
		out.println("<link rel=\"icon\" type=\"image/gif\" href=\"images/barrysboard.ico\">");
		out.println("<title>Barry's Board</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div align='center'>");
		out.println("<h1>Barry's Board</h1>");
		out.println("<a href=\"Index.html\"><img src=\"images/dry-erase-eraser-cartoon-clipart-1.jpg\" align=\"middle\" alt=\"Barry's Board\"/></a>");
		out.println("<ul>");
		out.println("<li><a class=\"active\" href=\"Index.html\">Home</a></li>");
		out.println("<li><a href=\"Upload.jsp\">Upload Files</a></li>");
		out.println("<li><a href=\"Teams.jsp\">Teams</a></li>");
		out.println("</ul>");
		out.println("</div>");
		out.println("<p>Thank you for submitting your file! It will be available shortly. Please use the menu links to navigate.");
		out.println("</body>");
		out.println("</html>");
	}

}
