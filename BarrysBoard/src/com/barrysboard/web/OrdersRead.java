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
		
		ArrayList<Orders> csrs = OrdersService.getOrdersList();
		ArrayList<BackLog> logs = BackLogService.getBackLogList();
		/*request.setAttribute("csrs", csrs);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerServiceRepresentativeList.jsp");
		dispatcher.forward(request, response);*/
		HashMap<String, Double> completion = new HashMap<>();
		for(int i = 0; i < csrs.size(); i++) {
			for(int j = 0; j < csrs.size(); j++) {
				if(csrs.get(i).getCsrID() == csrs.get(j).getCsrID()) {
					String name = csrs.get(i).getCsrName();
					
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.println("<body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>coid</td>");
		out.println("<td>csrid</td>");
		out.println("<td>csrname</td>");
		out.println("</tr>");
		for(Orders csr : csrs) {
			out.println("<tr>");
			out.println("<td>" + csr.getCompanyID() + "</td>");
			out.println("<td>" + csr.getCsrID() + "</td>");
			out.println("<td>" + csr.getCsrName() +"</td>");
			out.println("<td>" + csr.getBooked() +"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
