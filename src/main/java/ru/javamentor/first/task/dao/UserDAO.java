package ru.javamentor.first.task.dao;

import ru.javamentor.first.task.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;

    void addUser(User user) throws SQLException;

    boolean getUserByLogin(String login);

    User getUserById(long id) throws SQLException;

    boolean updateUser(long id, User upDateUser) throws SQLException;

    boolean deleteById(long id) throws SQLException;

    User getUserByLoginAndPassword(String login, String password) throws SQLException;

    void createTable() throws SQLException;
}
