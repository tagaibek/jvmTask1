package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {

    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }


    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    public void addUser(User user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public boolean getUserByLogin(String login) {
        Query query = session.createQuery("from User where login = :login");
        query.setParameter("login", login);
        return query.list().size() <= 0;
    }

    public User getUserById(long id) {
        Query query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);
        List<User> users = (List<User>) query.list();
        if (users.isEmpty()) return null;
        return users.get(0);
    }

    public boolean updateUser(long id, User upDateUser) {
        session.beginTransaction();
        String hql = "UPDATE User set " +
                "login = :login," +
                "password = :password, " +
                "name = :name ," +
                "secondName = :secondName, "+
                "mail = :mail "+
                "where id = :id";
        Query qr = session.createQuery(hql);
        qr.setParameter("login",upDateUser.getLogin());
        qr.setParameter("password",upDateUser.getPassword());
        qr.setParameter("name",upDateUser.getName());
        qr.setParameter("secondName",upDateUser.getSecondName());
        qr.setParameter("mail",upDateUser.getMail());
        qr.setParameter("id",id);
        int result = qr.executeUpdate();

        session.getTransaction().commit();
        session.close();
        return result!= 0;
    }

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
}
