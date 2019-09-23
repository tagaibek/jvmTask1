package ru.javamentor.first.task.servlet;

import ru.javamentor.first.task.model.User;
import ru.javamentor.first.task.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/allUsersServlet")
public class AllUsersServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> users = userService.getAllUsers();
            request.setAttribute("allUsers", users);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
