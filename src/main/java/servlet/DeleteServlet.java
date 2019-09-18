package servlet;

import service.UserService2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService2 userService2 = new  UserService2();
        long id = Long.parseLong(request.getParameter("delete"));
        boolean isDelete = userService2.deleteById(id);
        if (isDelete){
            response.sendRedirect(request.getContextPath() + "/allUsersServlet");
        }else {
            PrintWriter pr = response.getWriter();
            pr.println("<html>");
            pr.println("<h1> User was not delete ! </h1>");
            pr.println("</html>");
        }
    }
}
