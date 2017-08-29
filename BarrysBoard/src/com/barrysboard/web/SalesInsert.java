package com.barrysboard.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Sales;
import com.barrysboard.service.SalesService;

/**
 * Servlet implementation class SalesInsert
 */
@WebServlet("/SalesInsert")
public class SalesInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesInsert() {
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
		ArrayList<Sales> sales = SalesService.getSalesList();
		for(Sales sale : sales) {
			CustomerServiceRepresentative csr = new CustomerServiceRepresentative(sale.getCsrID(), "Needs Name");
			csr.insert();
			sale.insert();
			System.out.println(sale.getTotalAmount());
		}
	}

}
