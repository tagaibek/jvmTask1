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

    public boolean addUser(User user) throws IOException, SQLException {
        boolean hasUser = UserDAOFactory.getInstance().getUserByLogin(user.getLogin());
        if (hasUser) {
            UserDAOFactory.getInstance().addUser(user);
            return true;
        }
        return false;
    }
    public User getUserById(long id) throws IOException, SQLException {
        return UserDAOFactory.getInstance().getUserById(id);
    }

    public boolean updateUser(long id, User updateUser) throws IOException, SQLException {
           boolean isLoginTrue = UserDAOFactory.getInstance().getUserByLogin(updateUser.getLogin());
           if (isLoginTrue) {
               return  UserDAOFactory.getInstance().updateUser(id,updateUser);
           }
           return false;
    }

    public boolean deleteById(long id) throws IOException, SQLException {
        return UserDAOFactory.getInstance().deleteById(id);
    }

    public User getUserByLogin( String login, String password) throws IOException, SQLException {
        return UserDAOFactory.getInstance().getUserByLoginAndPassword(login,password);
    }
}
