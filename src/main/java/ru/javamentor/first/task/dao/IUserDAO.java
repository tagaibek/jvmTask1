package ru.javamentor.first.task.dao;

import ru.javamentor.first.task.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    List<User> getAllUsers() throws SQLException;

    void addUser(User user) throws SQLException;

    boolean getUserByLogin(String login);

    User getUserById(long id);

    boolean updateUser(long id, User upDateUser);

    boolean deleteById(long id);

    void createTable() throws SQLException;
}
