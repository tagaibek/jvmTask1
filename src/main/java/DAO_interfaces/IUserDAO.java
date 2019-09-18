package DAO_interfaces;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    List<User> getAllUsers();

    void addUser(User user) throws SQLException;

    boolean getUserByLogin(String login);

    User getUserById(long id);

    boolean updateUser(long id, User upDateUser);

    boolean deleteById(long id);
}
