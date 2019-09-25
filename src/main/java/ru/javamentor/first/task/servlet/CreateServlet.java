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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole().equals("admin")) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String name = request.getParameter("name");
            String secondName = request.getParameter("secondName");

            User UpdateUser = new User(role, login, password, name, secondName);
            try {
                boolean isAdd = userService.addUser(UpdateUser);
                if (isAdd) {
                    session.setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else {
                    PrintWriter pr = response.getWriter();
                    pr.println("<html>");
                    pr.println("<h1> The login already used! </h1>");
                    pr.println("</html>");
                }
            } catch (SQLException e) {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            response.setStatus(403);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
