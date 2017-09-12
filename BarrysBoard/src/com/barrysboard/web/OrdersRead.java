package com.barrysboard.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

/**
 * Servlet implementation class OrdersRead
 */
@WebServlet("/OrdersRead")
public class OrdersRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersRead() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		//ArrayList<Orders> csrs = OrdersService.getOrdersList();
		//ArrayList<BackLog> logs = BackLogService.getBackLogList();
		/*request.setAttribute("csrs", csrs);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerServiceRepresentativeList.jsp");
		dispatcher.forward(request, response);*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filePath = request.getParameter("path");
		
	}

}
