package com.barrysboard.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
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

import com.barrysboard.model.BackLog;
import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Loss;
import com.barrysboard.model.Orders;
import com.barrysboard.model.Sales;
import com.barrysboard.service.BackLogService;
import com.barrysboard.service.CustomerServiceRepresentativeService;
import com.barrysboard.service.LossService;
import com.barrysboard.service.OrdersService;
import com.barrysboard.service.PhoneActivityService;
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
		
		//String description = request.getParameter("filPath");
		//Part file = request.getPart("uploadFile");
		//String fileNombre = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		//InputStream fileContent = filePart.getInputStream();
		List<Part> fileParts = request.getParts().stream().filter(parts -> "uploadFile".equals(parts.getName())).collect(Collectors.toList());
		
		for(Part filePart : fileParts) {
			InputStream fileContent = filePart.getInputStream();
		if(reportType.equalsIgnoreCase("booked")) {
			ArrayList<Orders> orders = OrdersService.getOrdersList(fileContent);
			for (Orders order : orders) {
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(order.getCsrID(), "Empty", "E");
				csr.authenticate();
				order.authenticate();
			}
		} else if(reportType.equalsIgnoreCase("scheduled")) {
			ArrayList<Sales> sales = SalesService.getSalesList(fileContent);
			for(Sales sale : sales) {
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(sale.getCsrID(), "Empty", "E");
				csr.authenticate();
				sale.authenticate();
			}
		} else if(reportType.equalsIgnoreCase("loss")) {
			ArrayList<Loss> losses = LossService.getLossList(fileContent);
			for(Loss loss : losses) {
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(loss.getCsrID(), "Empty", "E");
				csr.authenticate();
				loss.authenticate();
			}
		} else if(reportType.equalsIgnoreCase("backlog")) {
			ArrayList<BackLog> backlogs = BackLogService.getBackLogList(fileContent);
			for(BackLog backlog : backlogs) {
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(backlog.getCsrID(), "Empty", "E");
				csr.authenticate();
				backlog.authenticate();
			}
		} else if(reportType.equalsIgnoreCase("employee")) {
			ArrayList<CustomerServiceRepresentative> csrs = CustomerServiceRepresentativeService.getCSRList(fileContent);
			for(CustomerServiceRepresentative csr : csrs) {
				csr.authenticate();
			}
		}
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='ISO-8859-1'>");
		out.println("<link rel='stylesheet' href='css/Main.css' />");
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
		out.println("</body>");
		out.println("</html>");
	}

}
