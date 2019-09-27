package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class UserDAOJDBC implements UserDAO {

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
            String role = rs.getString("role");
            String login = rs.getString("login");
            String pass = rs.getString("password");
            String name = rs.getString("name");
            String secondName = rs.getString("secondName");
            User user = new User(id, role,login, pass, name, secondName);
            users.add(user);
        }

        return users;
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users(login, role, password, name, secondName, mail) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getLogin());
        stmt.setString(2, user.getRole());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getName());
        stmt.setString(5, user.getSecondName());
        stmt.executeUpdate();
    }

    @Override
    public boolean getUserByLogin(String login) {
        String sql = "select * from users where login = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
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
                    result.getString("role"),
                    result.getString("login"),
                    result.getString("password"),
                    result.getString("name"),
                    result.getString("secondName"));
        }
        return user;
    }

    @Override
    public boolean updateUser(long id, User upDateUser) throws SQLException {
        String sql = "UPDATE users set password = ?, login = ?, password = ?, name = ?, secondName = ? where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,upDateUser.getRole());
        stmt.setString(2, upDateUser.getLogin());
        stmt.setString(3, upDateUser.getPassword());
        stmt.setString(4, upDateUser.getName());
        stmt.setString(5, upDateUser.getSecondName());
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
    public User getUserByLoginAndPassword(String login, String password) throws SQLException {
        User user = null;
        String sql = "select * from users where login = ? and password = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2,password);
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            user = new User(result.getLong("id"),
                    result.getString("role"),
                    result.getString("login"),
                    result.getString("password"),
                    result.getString("name"),
                    result.getString("secondName"));
        }
        return user;
    }

    @Override
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, role varchar(256), login varchar(256), " +
                "password varchar(256),  name varchar(256), secondName varchar(256), primary key (id))");
        stmt.close();
    }
}
