package com.barrysboard.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
		ArrayList<Orders> matchedOrders = null;
		
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
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
		out.println("</ul>");
		out.println("</div>");
		out.println("<p>Thank you for submitting your file! It will be available shortly. Please use the menu links to navigate.");
		if(!matchedOrders.isEmpty()) {
			out.println("<p>This is a list of orders with address matching:");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>csr</td>");
			out.println("<td>order ID</td>");
			out.println("<td>comm ID</td>");
			out.println("</tr>");
			for (Orders order : matchedOrders) {
				out.println("<tr>");
				out.println("<td>" + order.getCsrID() + "</td>");
				out.println("<td>" + order.getOrderID() + "</td>");
				out.println("<td>" + order.getCommID() + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
