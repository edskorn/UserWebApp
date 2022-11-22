package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;
@Repository
public class UserDaoHibernateImpl implements UserDao {
    @Autowired
    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("CREATE TABLE Users (id BIGINT NOT NULL AUTO_INCREMENT, Name varchar(255), LastName varchar(255), Age TINYINT, PRIMARY KEY (id)) ENGINE=InnoDB");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception sqlException) {
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeQuietly(session);
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("DROP TABLE IF EXISTS Users");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception sqlException) {
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeQuietly(session);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception sqlException) {
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeQuietly(session);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(getUserById(id));
            transaction.commit();
            System.out.println("\n>>> Student with id = " + id + " is successfully deleted!\n");
        } catch (Exception sqlException) {
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeQuietly(session);
        }
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> result = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = session.createQuery("SELECT a FROM User a", User.class).list();
            transaction.commit();
        } catch (Exception sqlException) {
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeQuietly(session);
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception sqlException) {
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeQuietly(session);
        }
    }

    public User getUserById(long id) {
        User user = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception sqlException) {
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeQuietly(session);
        }
        return user;
    }
}
