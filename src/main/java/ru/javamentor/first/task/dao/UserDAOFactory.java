package ru.javamentor.first.task.dao;

import ru.javamentor.first.task.util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class UserDAOFactory {

    private UserDAOFactory(){}

    public static IUserDAO getInstance() throws IOException {
        Properties property = new Properties();
        try(InputStream is = UserDAOFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            Objects.requireNonNull(is, "InputStream is null.");
            property.load(is);
            String connect = property.getProperty("connect");
            if (connect.equals("Hibernate")) {
                return new UserDAOHibernate(DBHelper.getSessionFactory().openSession());
            } else { // default instance is always JDBC
                return new UserDAOJDBC(DBHelper.getMysqlConnection());
            }
        }
    }
}
