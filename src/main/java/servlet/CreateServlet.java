package servlet;

import model.User;
import service.UserService2;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService2 userService2 = new  UserService2();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String secondName = request.getParameter("secondName");
        String mail = request.getParameter("mail");
        User user = new User(login,password,name,secondName,mail);
        try {
            boolean isAdd = userService2.addUser(user);
            if (isAdd) {
                response.sendRedirect(request.getContextPath() + "/allUsersServlet");
            }else {
                PrintWriter pr = response.getWriter();
                pr.println("<html>");
                pr.println("<h1> The login already used! </h1>");
                pr.println("</html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
