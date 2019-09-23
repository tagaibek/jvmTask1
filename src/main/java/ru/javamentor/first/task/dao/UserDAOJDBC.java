package ru.javamentor.first.task.dao;

import ru.javamentor.first.task.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class UserDAOJDBC implements IUserDAO {

    private Connection connection;

    UserDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        String sql = "select * from users";
        List<User> users = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            long id = rs.getLong("id");
            String login = rs.getString("login");
            String pass = rs.getString("password");
            String name = rs.getString("name");
            String secondName = rs.getString("secondName");
            String mail = rs.getString("mail");
            User user = new User(id, login, pass, name, secondName, mail);
            users.add(user);
        }

        return users;
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users(login, password, name, secondName, mail) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getLogin());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getName());
        stmt.setString(4, user.getSecondName());
        stmt.setString(5, user.getMail());
        stmt.executeUpdate();
    }

    @Override
    public boolean getUserByLogin(String login) {
        String sql = "select * from users where login = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserById(long id) throws SQLException {
        String sql = "select * from users where id = ?";
        User user = null;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            user = new User(result.getLong("id"),
                    result.getString("login"),
                    result.getString("password"),
                    result.getString("name"),
                    result.getString("secondName"),
                    result.getString("mail"));
        }
        return user;
    }

    @Override
    public boolean updateUser(long id, User upDateUser) throws SQLException {
        String sql = "UPDATE users set login = ?, password = ?, name = ?, secondName = ?, mail =? where id = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, upDateUser.getLogin());
        stmt.setString(2, upDateUser.getPassword());
        stmt.setString(3, upDateUser.getName());
        stmt.setString(4, upDateUser.getSecondName());
        stmt.setString(5, upDateUser.getMail());
        stmt.setLong(6, id);
        stmt.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteById(long id) throws SQLException {
        String sql = "delete   from users where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.execute();
        return true;
    }

    @Override
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, login varchar(256), " +
                "password varchar(256),  name varchar(256), secondName varchar(256), mail varchar(256), primary key (id))");
        stmt.close();
    }
}
