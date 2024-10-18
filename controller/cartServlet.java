package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class cartServlet
 */
import dao.MovieDAO;
import model.Movie;
@WebServlet("/cartServlet")
public class cartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cartServlet() {
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
        
        // Get the current cart (create a new one if it doesn't exist)
        List<Movie> cart = (List<Movie>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        
        // Get the movie ID from the request
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        
        // Retrieve the movie from the database
        Movie movie = MovieDAO.getMovieById(movieId).orElse(null);
        
        if (movie != null) {
            // Add the movie to the cart
            cart.add(movie);
            session.setAttribute("cart", cart);
        }
        
        // Redirect back to the movie list or cart page
        response.sendRedirect("movie-list.jsp"); // Redirect to movie list or cart page
		doGet(request, response);
	}

}
