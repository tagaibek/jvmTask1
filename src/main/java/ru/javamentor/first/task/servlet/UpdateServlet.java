package ru.javamentor.first.task.servlet;

import ru.javamentor.first.task.model.User;
import ru.javamentor.first.task.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String secondName = request.getParameter("second_name");
        String mail = request.getParameter("mail");
        User updateUser = new User(login, password, name, secondName, mail);
        try {
            boolean upDate = userService.updateUser(id, updateUser);
            if (upDate) {
                response.sendRedirect(request.getContextPath() + "/allUsersServlet");
            } else {
                PrintWriter pr = response.getWriter();
                pr.println("<html>");
                pr.println("<h1> The login already used! </h1>");
                pr.println("</html>");
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("edit"));
        try {
            User user = userService.getUserById(id);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/update.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
