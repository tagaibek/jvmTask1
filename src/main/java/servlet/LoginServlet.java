package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet( "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final String roleAdmin = "admin";
    private final String roleUser = "user";
    private UserService userService = UserService.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            User user = userService.getUserByLogin(login,password);
            if (user!= null) {
               if (user.getRole().equals(roleAdmin)){
                   HttpSession session = request.getSession();
                   session.setAttribute("user", user);
                   Cookie userRole = new Cookie("user", user.getRole());
                   response.addCookie(userRole);
                   response.sendRedirect("/admin");
               }
               else if (user.getRole().equals(roleUser)){
                   HttpSession session = request.getSession();
                   session.setAttribute("user", user);
                   Cookie userRole = new Cookie("user", user.getRole());
                   response.addCookie(userRole);
                   response.sendRedirect("/user");
               }
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/registrationPage.html");
                PrintWriter out= response.getWriter();
                out.println("<font color=red>Either user name or password is wrong.</font>");
                rd.include(request, response);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registrationPage.html").forward(request, response);
    }


}
