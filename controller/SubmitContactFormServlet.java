package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SubmitContactFormServlet
 */
@WebServlet("/SubmitContactFormServlet")
public class SubmitContactFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitContactFormServlet() {
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
		 HttpSession session = request.getSession();

	        // Get form data
	        String email = request.getParameter("email");
	        String message = request.getParameter("message");

	        // Process the message (e.g., save to database or send email)
	        System.out.println("Email: " + email);
	        System.out.println("Message: " + message);
	        // Set a success message in the session
	        session.setAttribute("successMessage", "Your message has been submitted successfully!");

	        // Redirect back to the contact us page
	        response.sendRedirect("contactUs.jsp");
	    
	}

}
