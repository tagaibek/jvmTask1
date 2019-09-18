package service;

import DAO.UserDAO2;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService2 {

    public UserService2(){
    }

    public List<User> getAllUsers() {
        return getUserDAO2().getAllUsers();
    }


    public boolean addUser(User user) throws SQLException   {
        boolean hasUser = getUserDAO2().getUserByLogin(user.getLogin());
        if (!hasUser) {
            getUserDAO2().addUser(user);
            return true;
        }
        return false;
    }

    public User getUserById(long id) {
        return getUserDAO2().getUserById(id);
    }

    public boolean updateUser(long id, User updateUser) {
        boolean isLoginTrue = getUserDAO2().getUserByLogin(updateUser.getLogin());
        if (!isLoginTrue) {
            return  getUserDAO2().updateUser(id,updateUser);
        }
        return false;
    }

    public boolean deleteById(long id) {
        return getUserDAO2().deleteById(id);
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("uaser_table?").           //db name
                    append("user=root&").          //login
                    append("password=1234");       //password

            // System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
//            throw new IllegalStateException();
        }

        return null;
    }

    private static UserDAO2 getUserDAO2() {
        return new UserDAO2(getMysqlConnection());
    }

    public void createTable() throws DBException {
        UserDAO2 dao = getUserDAO2();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
