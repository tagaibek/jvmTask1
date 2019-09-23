package ru.javamentor.first.task.servlet;

import ru.javamentor.first.task.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
   /* UserService2 userService2 = new  UserService2();*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String secondName = request.getParameter("second_name");
        String mail = request.getParameter("mail");
        User updateUser = new User(login, password, name, secondName, mail);

     /*   boolean upDate = userService2.updateUser(id, updateUser);*/

       /* if (upDate) {
            response.sendRedirect(request.getContextPath() + "/allUsersServlet");
        } else {
            PrintWriter pr = response.getWriter();
            pr.println("<html>");
            pr.println("<h1> The login already used! </h1>");
            pr.println("</html>");
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* long id = Long.parseLong(request.getParameter("edit"));
        User user = userService2.getUserById(id);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/update.jsp").forward(request, response);
        }*/
    }
}
