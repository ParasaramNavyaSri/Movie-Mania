package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Movie;

/**
 * Servlet implementation class removeFromCartServlet
 */
@WebServlet("/removeFromCartServlet")
public class removeFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeFromCartServlet() {
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
	 @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();
		 
	        List<Movie> cart = (List<Movie>) session.getAttribute("cart");
	        if (cart != null) {
	            int movieIndex = Integer.parseInt(request.getParameter("movieIndex"));
	            if (movieIndex >= 0 && movieIndex < cart.size()) {
	                cart.remove(movieIndex); // Remove the movie at the selected index
	            }
	        }
	        response.sendRedirect("cart.jsp"); // Redirect back to the cart page
		doGet(request, response);
	}

}
