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
import com.barrysboard.service.BackLogService;
import com.barrysboard.service.CustomerServiceRepresentativeService;
import com.barrysboard.service.OrdersService;
import com.barrysboard.service.PhoneActivityService;

/**
 * Servlet implementation class OrdersInsert
 */
@WebServlet("/OrdersInsert")
public class OrdersInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		String fileName = request.getParameter("filePath");
		File file = new File(fileName);
		ArrayList<Orders> orders = PhoneActivityService.getOrdersList(file);
		ArrayList<BackLog> backLogs = PhoneActivityService.getBackLogList(file);
		ArrayList<CustomerServiceRepresentative> csrs = PhoneActivityService.getCSRList(file);
		
		for(CustomerServiceRepresentative csr : csrs)
			csr.authenticate();
		
		for(Orders order : orders)
			order.authenticate();
		
		for(BackLog backLog : backLogs)
			backLog.authenticate();*/
	}

}
