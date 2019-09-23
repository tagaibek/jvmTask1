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

@WebServlet( "/CreateServlet")
public class CreateServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String secondName = request.getParameter("secondName");
        String mail = request.getParameter("mail");
        User user = new User(login, password, name, secondName, mail);
        try {
            boolean isAdd = userService.addUser(user);
            if (isAdd) {
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

    }
}
