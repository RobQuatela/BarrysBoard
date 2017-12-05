package com.barrysboard.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.barrysboard.model.CustomerServiceRepresentative;

/**
 * Servlet implementation class EmployeeInsert
 */
@WebServlet("/EmployeeInsert")
public class EmployeeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CustomerServiceRepresentative> csrs = CustomerServiceRepresentative.getCSRs(
				request.getParameter("empNameNo"), request.getParameter("empStatus"), request.getParameter("empRole"));
		request.setAttribute("csrs", csrs);
		HttpSession session = request.getSession();
		session.setAttribute("csrs", csrs);
		request.setAttribute("csrs", csrs);
		request.getRequestDispatcher("Employees.jsp").forward(request, response);
		//response.sendRedirect("Employees.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btnInsertEmp = request.getParameter("btnInsertEmp");
		String btnUpdateEmp = request.getParameter("btnUpdateEmp");
		
		if(btnInsertEmp != null) {
			CustomerServiceRepresentative emp = new CustomerServiceRepresentative(request.getParameter("empID"),
					request.getParameter("empName"), "A", request.getParameter("empRole"));
			emp.authenticate();
			System.out.println("Yay! Employee is in");
			response.sendRedirect("Employees.jsp");
		}
		
		if(btnUpdateEmp != null) {
			String[] ids = request.getParameterValues("ckID");
			ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
			for(String id : ids) {
				csrs.add(new CustomerServiceRepresentative(
						id, request.getParameter("txtName" + id), 
						request.getParameter("txtStatus" + id),request.getParameter("txtRole" + id)));
				System.out.println("CSR ID: " + id + " name: " + request.getParameter("txtName" + id) + "");
			}
			
			for(CustomerServiceRepresentative csr : csrs) 
				csr.update();

			response.sendRedirect("Employees.jsp");
		}
		
	}

}
