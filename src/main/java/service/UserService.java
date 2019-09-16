package service;


import DAO.UserDAO;
import main.java.model.User;
import org.hibernate.SessionFactory;

import java.util.List;


public class UserService {

    private static UserService userService;

    private SessionFactory sessionFactory;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(main.java.util.DBHelper.getSessionFactory());
        }
        return userService;
    }

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAllUsers(){
        return new UserDAO(sessionFactory.openSession()).getAllUsers();
    }

    public boolean addUser(User user){
        boolean hasUser = new UserDAO(sessionFactory.openSession()).getUserByLogin(user.getLogin());
        if (hasUser){
            new UserDAO(sessionFactory.openSession()).addUser(user);
            return true;
        }
        return false;
    }

    public User getUserById(long id) {
        UserDAO userDAO = new UserDAO(sessionFactory.openSession());
        return userDAO.getUserById(id);
    }

    public boolean upDateUser(long id, User upDateUser) {
           boolean isLoginTrue = new UserDAO(sessionFactory.openSession()).getUserByLogin(upDateUser.getLogin());
           if (!isLoginTrue){
               return  new UserDAO(sessionFactory.openSession()).updateUser(id,upDateUser);
           }
           return false;
    }

    public boolean deleteById(long id) {
        return new UserDAO(sessionFactory.openSession()).deleteById(id);
    }
}
