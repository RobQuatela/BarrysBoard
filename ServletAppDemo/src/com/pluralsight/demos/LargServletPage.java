package com.pluralsight.demos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LargServletPage
 */
@WebServlet("/LargServletPage")
public class LargServletPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LargServletPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out;
		
		if(GzipUtility.isGzipSupported(request) && !GzipUtility.isGzipDisabled(request)) {
			out = GzipUtility.getGzipWriter(response);
			response.setHeader("content-Encoding", "gzip");
		}
		else {
			out = response.getWriter();
		}
		
		String dummyLine = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum in nisl ut nibh efficitur blandit. Sed aliquam mauris eu eleifend rutrum. Fusce elementum purus a urna malesuada, non vestibulum mi consectetur. Suspendisse potenti. Integer porta, justo at blandit fermentum, nunc tellus placerat magna, eu euismod ipsum risus id urna. Ut tristique, neque ut eleifend vulputate, ipsum ante condimentum massa, id volutpat lacus lectus sed quam. Pellentesque nec tempus dolor. Praesent sollicitudin volutpat iaculis. Duis fringilla purus lectus, sit amet fringilla eros porta quis. Suspendisse auctor aliquet neque, et tristique lacus convallis et. Mauris sit amet purus dapibus, dignissim tellus non, vehicula ipsum. ";
		
		out.println("<!Doctype HTML>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Demo: Request Headers</title>");
		out.println("</head>");
		out.println("<body>");
		
		for(int i = 0; i < 10000; i++) {
			out.println(dummyLine + "</br>");
		}
		
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
