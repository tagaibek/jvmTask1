package ru.javamentor.first.task.servlet;

import ru.javamentor.first.task.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet( "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("delete"));
        try {
            boolean isDelete = userService.deleteById(id);
            if (isDelete) {
                response.sendRedirect(request.getContextPath() + "/allUsersServlet");
            }else {
                PrintWriter pr = response.getWriter();
                pr.println("<html>");
                pr.println("<h1> User was not delete ! </h1>");
                pr.println("</html>");
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }
}
