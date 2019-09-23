package ru.javamentor.first.task.dao;

import ru.javamentor.first.task.util.DBHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDAOFactory {

    private UserDAOFactory(){}

    public static IUserDAO getInstance() throws IOException {
        Properties property = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        property.load(fis);
        String connect = property.getProperty("connect");

        if (connect.equals("Hibernate")) {
            return new UserDAOHibernate(DBHelper.getSessionFactory().openSession());
        } else { // default instance is always JDBC
            return new UserDAOJDBC(DBHelper.getMysqlConnection());
        }
    }
}
