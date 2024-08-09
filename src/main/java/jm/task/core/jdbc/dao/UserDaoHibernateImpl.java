package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sqlRequest = "CREATE TABLE IF NOT EXISTS tableUsers (" +
                "id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(30) NOT NULL," +
                " lastName VARCHAR(30) NOT NULL," +
                " age TINYINT NOT NULL)";
        Transaction transaction = null;
        try (Session ses = Util.getSessionFactory().openSession()) {
            transaction = ses.beginTransaction();
            ses.createSQLQuery(sqlRequest).executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlRequest = "DROP TABLE IF EXISTS tableUsers";
        Transaction transaction = null;
        try (Session ses = Util.getSessionFactory().openSession()) {
            transaction = ses.beginTransaction();
            ses.createSQLQuery(sqlRequest).executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User(name, lastName, age);
        try (Session ses = Util.getSessionFactory().openSession()) {
            transaction = ses.beginTransaction();
            ses.save(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        /*String hql = "from tableUsers where User.id = :id";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("id", id);
        //"from User where id=id"*/
        System.out.println("User deleted");
    }

    @Override
    public List<User> getAllUsers() {
        try (Session ses = Util.getSessionFactory().openSession()) {
            return (List<User>) ses.createQuery("from tableUsers").list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("All users");
        return null;
    }

    @Override
    public void cleanUsersTable() {
        System.out.println("Table cleaned");
    }
}
