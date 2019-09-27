package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole().equals("admin")){
            try {
                List<User> users = userService.getAllUsers();
                request.setAttribute("allUsers", users);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (SQLException e) {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }else {
            response.setStatus(403);
        }
    }
}
