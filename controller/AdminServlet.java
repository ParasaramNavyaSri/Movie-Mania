package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MovieDAO;
import model.Movie;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action != null ? action : "") {
                case "list":
                    listMovies(request, response);
                    break;
                case "delete":
                    deleteMovie(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    showNewForm(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action != null ? action : "") {
                case "insert":
                    insertMovie(request, response);
                    break;
                case "update":
                    updateMovie(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Movie existingMovie = MovieDAO.getMovieById(id).orElse(null);
        
        if (existingMovie == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
            return;
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-form.jsp");
        request.setAttribute("movie", existingMovie);
        dispatcher.forward(request, response);
    }

    private void listMovies(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Movie> listMovies = MovieDAO.getAllMovies();
        request.setAttribute("listMovies", listMovies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("movie", new Movie()); // Create a new Movie object for the form
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        double price = Double.parseDouble(request.getParameter("price"));
        String showTime = request.getParameter("show_time");
        String imageUrl = request.getParameter("image_url"); // Get imageUrl from request

        Movie newMovie = new Movie(title, genre, price, showTime, imageUrl); // Pass imageUrl
        MovieDAO.insertMovie(newMovie);

        HttpSession session = request.getSession();
        session.setAttribute("message", "Movie inserted successfully!");
        response.sendRedirect("AdminServlet?action=list");
    }
    
    private void updateMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        
        String genre = request.getParameter("genre");
        double price = Double.parseDouble(request.getParameter("price"));
        String showTime = request.getParameter("show_time");
        String imageUrl = request.getParameter("image_url"); // Get imageUrl from request

        Movie movie = new Movie(id, title, genre, price, showTime, imageUrl); // Pass imageUrl
        MovieDAO.updateMovie(movie);

        HttpSession session = request.getSession();
        session.setAttribute("message", "Movie updated successfully!");
        response.sendRedirect("AdminServlet?action=list");
    }

    private void deleteMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        MovieDAO.deleteMovie(id);
        response.sendRedirect("AdminServlet?action=list");
    }
}
