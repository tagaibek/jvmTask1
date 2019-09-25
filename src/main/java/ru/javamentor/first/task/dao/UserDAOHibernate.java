package ru.javamentor.first.task.dao;

import ru.javamentor.first.task.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

class UserDAOHibernate  implements UserDAO {

    private Session session;

    public UserDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean getUserByLogin(String login) {
        Query query = session.createQuery("from User where login = :login");
        query.setParameter("login", login);
        return query.list().size() <= 0;
    }

    @Override
    public User getUserById(long id) {
        Query query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);
        List<User> users = (List<User>) query.list();
        if (users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    public boolean updateUser(long id, User upDateUser) {
        session.beginTransaction();
        String hql = "UPDATE User set " +
                "login = :login," +
                "password = :password, " +
                "name = :name ," +
                "role = :role," +
                "secondName = :secondName "+
                "where id = :id";
        Query qr = session.createQuery(hql);
        qr.setParameter("login",upDateUser.getLogin());
        qr.setParameter("password",upDateUser.getPassword());
        qr.setParameter("name",upDateUser.getName());
        qr.setParameter("role",upDateUser.getRole());
        qr.setParameter("secondName",upDateUser.getSecondName());
        qr.setParameter("id",id);
        int result = qr.executeUpdate();

        session.getTransaction().commit();
        session.close();
        return result!= 0;
    }

    @Override
    public boolean deleteById(long id) {
        session.beginTransaction();
        String hql = "delete from User where id= :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result != 0;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        Query query = session.createQuery("from User where login = ? and password = ?");
        query.setParameter(0, login);
        query.setParameter(1, password);
        List<User> users = (List<User>) query.list();
        if (users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    public void createTable() throws SQLException {

    }
}
