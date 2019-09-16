package servlet;

import main.java.model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String secondName = request.getParameter("second_name");
        String mail = request.getParameter("mail");
        User upDateUser = new User(login,password,name,secondName,mail);

        boolean upDate = UserService.getInstance().upDateUser(id,upDateUser);

        if (upDate) {
            response.sendRedirect(request.getContextPath() + "/allUsersServlet");
        }else {
            PrintWriter pr = response.getWriter();
            pr.println("<html>");
            pr.println("<h1> The login already used! </h1>");
            pr.println("</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("edit"));
        User user = UserService.getInstance().getUserById(id);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/update.jsp").forward(request, response);
        }
    }
}
