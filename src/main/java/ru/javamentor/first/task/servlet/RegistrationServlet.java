package ru.javamentor.first.task.servlet;

import ru.javamentor.first.task.model.User;
import ru.javamentor.first.task.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet( "/registration")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            User user = userService.getUserByLogin(login,password);
            if (user!= null) {
                if (user.getRole().equals("user")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/user");
                }
                else if (user.getRole().equals("admin")){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/admin");
                }
            } else {
                response.setStatus(403);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registrationPage.html").forward(request, response);
    }


}
