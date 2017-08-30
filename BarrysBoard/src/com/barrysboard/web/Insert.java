package com.barrysboard.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barrysboard.model.BackLog;
import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Orders;
import com.barrysboard.model.Sales;
import com.barrysboard.service.PhoneActivityService;
import com.barrysboard.service.SalesService;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
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
		String fileName = request.getParameter("filePath");
		String reportType = request.getParameter("reportType");
		File file = new File(fileName);
		
		if(reportType.equalsIgnoreCase("phone activity")) {
			ArrayList<Orders> orders = PhoneActivityService.getOrdersList(file);
			ArrayList<BackLog> backLogs = PhoneActivityService.getBackLogList(file);
			ArrayList<CustomerServiceRepresentative> csrs = PhoneActivityService.getCSRList(file);

			for (CustomerServiceRepresentative csr : csrs)
				csr.authenticate();

			for (Orders order : orders)
				order.authenticate();

			for (BackLog backLog : backLogs)
				backLog.authenticate();
		} else if(reportType.equalsIgnoreCase("scheduled jobs")) {
			ArrayList<Sales> sales = SalesService.getSalesList(file);
			for(Sales sale : sales) {
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(sale.getCsrID(), "Empty");
				csr.authenticate();
				sale.authenticate();
			}
		}
	}

}
