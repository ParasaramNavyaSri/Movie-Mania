package controller;

import dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
  

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("uname");
        String password = request.getParameter("pwd");
        int userId = -1; // Default to -1 for invalid login

        try {
            userId = UserDAO.loginUser(username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle exception properly in production
        }

        if (userId != -1) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("loginMessage", "Login successful!"); 
            response.sendRedirect("movie-list.jsp");
        } else {
            response.sendRedirect("movie-list.jsp?error=invalid");
        }
    }
}