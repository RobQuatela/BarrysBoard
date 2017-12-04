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
		else
			response.sendRedirect("UploadComplete.jsp");
	}
}
