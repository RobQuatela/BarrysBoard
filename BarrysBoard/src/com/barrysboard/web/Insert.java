package com.barrysboard.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Orders;
import com.barrysboard.service.BackLogService;
import com.barrysboard.service.CustomerServiceRepresentativeService;
import com.barrysboard.service.IncompletesService;
import com.barrysboard.service.LossService;
import com.barrysboard.service.OrdersService;
import com.barrysboard.service.SalesService;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
@MultipartConfig
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
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
		String reportType = request.getParameter("reportType");
		List<Part> fileParts = request.getParts().stream().filter(parts -> "uploadFile".equals(parts.getName())).collect(Collectors.toList());
		//ArrayList<Orders> matchedOrders = null;
		HashMap<String, Orders> matchedOrders = new HashMap<>();
		
		for(Part filePart : fileParts) {
			InputStream fileContent = filePart.getInputStream();
			if (reportType.equalsIgnoreCase("booked")) {
				matchedOrders = OrdersService.readOrders(fileContent);
			} else if (reportType.equalsIgnoreCase("scheduled")) {
				SalesService.readSales(fileContent);	
			} else if(reportType.equalsIgnoreCase("incomplete")) {
				IncompletesService.readIncompletes(fileContent);
			} else if (reportType.equalsIgnoreCase("loss")) {
				LossService.readLoss(fileContent);
			} else if (reportType.equalsIgnoreCase("commSale")) {
				SalesService.readSalesCOMM(fileContent);
			} else if (reportType.equalsIgnoreCase("employee")) {
				CustomerServiceRepresentativeService.readCSR(fileContent);
			}
		}
		request.getSession().setAttribute("matchedOrders", matchedOrders);
		request.setAttribute("matchingOrders", matchedOrders);
		if(!matchedOrders.isEmpty())
			request.getRequestDispatcher("MatchedOrders.jsp").forward(request, response);
		else {
		/*response.setContentType("text/html");
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
		if(!matchedOrders.isEmpty()) {
			out.println("<form name='matchedOrders' method='GET' action='InsertMatched'>");
			out.println("<h3>There are <b>" + matchedOrders.size() + "</b> orders listed below have been matched to previous orders " +
			"in the last 30 days. Do you want to accept these changes?</h3><br />");
			out.println("<input type='submit' name='btnSubmit' value='Insert Selected'>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>Company</th>");
			out.println("<th>Date Booked</th>");
			out.println("<th>CSR</th>");
			out.println("<th>Current Order No.</th>");
			out.println("<th>Matched Order No. </th>");
			out.println("<th>Estimator</th>");
			out.println("<th>Job Type</th>");
			out.println("<th>Address</th>");
			out.println("<th>Insert?</th>");
			out.println("</tr>");
			Iterator it = matchedOrders.entrySet().iterator();
/*			for (Orders order : matchedOrders) {
				out.println("<tr>");
				out.println("<td>" + order.getCompanyID() + "</td>");
				out.println("<td>" + order.getDate().getMonthValue() + "/" + order.getDate().getDayOfMonth() + "/" + order.getDate().getYear() + "</td>");
				out.println("<td>" + CustomerServiceRepresentative.getName(order.getCsrID()) + "</td>");
				out.println("<td>" + order.getOrderID() + "</td>");
				out.println("<td>" + CustomerServiceRepresentative.getName(order.getCommID()) + "</td>");
				out.println("<td>" + order.getJobType() + "</td>");
				out.println("<td>" + order.getAddress() + ", " + order.getState() + " " + order.getZip() + "</td>");
				out.println("<td><input type='checkbox' name='ckInsert' value='ckYes" + order.getOrderID() + "'></td>");
				out.println("</tr>");
			}
			for(Map.Entry<String, Orders> order : matchedOrders.entrySet()) {
				out.println("<tr>");
				out.println("<td>" + order.getValue().getCompanyID() + "</td>");
				out.println("<td>" + order.getValue().getDate().getMonthValue() + "/" + order.getValue().getDate().getDayOfMonth() + "/" + order.getValue().getDate().getYear() + "</td>");
				out.println("<td>" + CustomerServiceRepresentative.getName(order.getValue().getCsrID()) + "</td>");
				out.println("<td>" + order.getValue().getOrderID() + "</td>");
				out.println("<td>" + order.getKey() + "</td>");
				out.println("<td>" + CustomerServiceRepresentative.getName(order.getValue().getCommID()) + "</td>");
				out.println("<td>" + order.getValue().getJobType() + "</td>");
				out.println("<td>" + order.getValue().getAddress() + ", " + order.getValue().getState() + " " + order.getValue().getZip() + "</td>");
				out.println("<td><input type='checkbox' name='ckInsert' value='ckYes" + order.getValue().getOrderID() + "'></td>");
				out.println("</tr>");
			}
			out.println("</table><br />");
			//request.getSession().setAttribute("matchedOrders", matchedOrders);
			out.println("</form>");
		}
		out.println("</body>");
		out.println("</html>");
		}*/
		}
	}
}
