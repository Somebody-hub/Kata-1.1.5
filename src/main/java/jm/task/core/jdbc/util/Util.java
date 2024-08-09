package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Properties;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";
    private static final String URL = "jdbc:mysql://localhost:3306/katapp114";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER_NAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, DIALECT);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(jm.task.core.jdbc.model.User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception ex) {
                System.out.println("Connection failed");
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName(DRIVER).getDeclaredConstructor().newInstance();
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed");
            ex.printStackTrace();
        }
        return connection;
    }

    //Connection close???
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}