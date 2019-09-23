package ru.javamentor.first.task.service;


import ru.javamentor.first.task.dao.UserDAOFactory;
import ru.javamentor.first.task.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class UserService {

    private UserService(){ // hiding default constructor in singleton class.
    }

    private static class UserServiceHolder{
        private final static UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.instance;
    }

    public List<User> getAllUsers() throws SQLException, IOException {
        return UserDAOFactory.getInstance().getAllUsers();
    }

   /* public boolean addUser(User user) throws IOException, SQLException {
        IUserDAO iUserDAO = ConnectionFactoryDAO.getConnectionFactory();
        boolean hasUser = iUserDAO.getUserByLogin(user.getLogin());
        if (hasUser) {
           iUserDAO.addUser(user);
            return true;
        }
        return false;
    }
*/
  /*  public User getUserById(long id) {
        IUserDAO userDAO = new UserDAO(connectionFactoryDAO.openSession());
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
    }*/
}
