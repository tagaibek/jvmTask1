package servlet;

import exception.DBException;
import model.User;
import service.UserService2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet( "/allUsersServlet")
public class AllUsersServlet extends HttpServlet {
    UserService2 userService2 = new UserService2();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users =  userService2.getAllUsers();
        request.setAttribute("allUsers", users);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService2 bankClientService = new UserService2();
        try {
            bankClientService.createTable();
            resp.setStatus(200);
        } catch (DBException | SQLException e) {
            resp.setStatus(400);
        }
    }
}
