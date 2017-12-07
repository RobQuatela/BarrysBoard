package com.barrysboard.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Team;

/**
 * Servlet implementation class TeamController
 */
@WebServlet("/TeamController")
public class TeamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamController() {
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
		
		if(request.getParameter("btnAddTeam") != null) {
			Team team = new Team(
					request.getParameter("teamName").toString(), 1,
					request.getParameter("ddlTeamLead").toString());
			team.insert();
			response.sendRedirect("Teams.jsp");
		}
		
		if(request.getParameter("btnRefresh") != null) {
			request.setAttribute("teamNo", request.getParameter("lstTeam"));
			request.getRequestDispatcher("Teams.jsp").forward(request, response);
		}
		
		if(request.getParameter("btnAddToTeam") != null) {
			CustomerServiceRepresentative csr = new CustomerServiceRepresentative(
					request.getParameter("lstCsrElligble"));
			int teamNo = Integer.parseInt(request.getParameter("teamId").toString());
			String result = csr.addToTeam(teamNo);
			System.out.println(result);
			response.sendRedirect("Teams.jsp");
		}
		
		String[] ids = request.getParameterValues("btnDel");
		for(String id : ids) {
			if(id != null) {
				//CustomerServiceRepresentative csr = new CustomerServiceRepresentative(
				//		request.getParameter("lst)
			}
		}
	}

}
