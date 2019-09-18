package service;


import DAO.UserDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;


public class UserService {

    private static UserService userService;

    private SessionFactory sessionFactory;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAllUsers() {
        return new UserDAO(sessionFactory.openSession()).getAllUsers();
    }

    public boolean addUser(User user) {
        boolean hasUser = new UserDAO(sessionFactory.openSession()).getUserByLogin(user.getLogin());
        if (hasUser) {
            new UserDAO(sessionFactory.openSession()).addUser(user);
            return true;
        }
        return false;
    }

    public User getUserById(long id) {
        UserDAO userDAO = new UserDAO(sessionFactory.openSession());
        return userDAO.getUserById(id);
    }

    public boolean updateUser(long id, User updateUser) {
           boolean isLoginTrue = new UserDAO(sessionFactory.openSession()).getUserByLogin(updateUser.getLogin());
           if (isLoginTrue) {
               return  new UserDAO(sessionFactory.openSession()).updateUser(id,updateUser);
           }
           return false;
    }

    public boolean deleteById(long id) {
        return new UserDAO(sessionFactory.openSession()).deleteById(id);
    }
}
