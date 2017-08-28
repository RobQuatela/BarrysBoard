package com.barrysboard.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.service.CustomerServiceRepresentativeService;

/**
 * Servlet implementation class CustomerServiceRepresentativeInsert
 */
@WebServlet("/CustomerServiceRepresentativeInsert")
public class CustomerServiceRepresentativeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServiceRepresentativeInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<CustomerServiceRepresentative> csrs = CustomerServiceRepresentativeService.getCSRList();
		response.setContentType("text/html");
		response.getWriter().println("<body>");
		for(CustomerServiceRepresentative csr : csrs) {
			csr.insert();
			response.getWriter().println("<p>" + csr.getCsrID() + " " + csr.getCsrName() + "</p>");
		}
		response.getWriter().println("</body>");
	}

}