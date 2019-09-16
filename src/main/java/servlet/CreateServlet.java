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

@WebServlet( "/CreateServlet")
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String secondName = request.getParameter("secondName");
        String mail = request.getParameter("mail");
        User user = new User(login,password,name,secondName,mail);
        boolean isAdd =  UserService.getInstance().addUser(user);
        if (isAdd){
            PrintWriter pr = response.getWriter();
            pr.println("<html>");
            pr.println("<h1> User was added</h1>");
            pr.println("</html>");
        }else {
            PrintWriter pr = response.getWriter();
            pr.println("<html>");
            pr.println("<h1> User was not added</h1>");
            pr.println("</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
