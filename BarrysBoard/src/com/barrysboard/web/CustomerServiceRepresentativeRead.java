package com.barrysboard.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.service.CustomerServiceRepresentativeService;

/**
 * Servlet implementation class CustomerServiceRepresentativeRead
 */
@WebServlet("/CustomerServiceRepresentativeRead")
public class CustomerServiceRepresentativeRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CustomerServiceRepresentativeRead() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
/*		ArrayList<CustomerServiceRepresentative> csrs = CustomerServiceRepresentativeService.getCSRList();
		request.setAttribute("csrs", csrs);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerServiceRepresentativeList.jsp");
		dispatcher.forward(request, response);
		PrintWriter out = response.getWriter();
		out.println("<body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>coid</td>");
		out.println("<td>csrid</td>");
		out.println("<td>csrname</td>");
		out.println("</tr>");
		for(CustomerServiceRepresentative csr : csrs) {
			out.println("<tr>");
			out.println("<td>" + csr.getCsrID() + "</td><td>" + csr.getCsrName() +"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
