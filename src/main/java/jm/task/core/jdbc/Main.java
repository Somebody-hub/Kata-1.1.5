package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDao ud = new UserDaoHibernateImpl();
        //UserDaoHibernateImpl //UserDaoJDBCImpl

        UserService userServ = new UserServiceImpl(ud);
        //Исправил Dao на Service


        userServ.createUsersTable();
        for (int i = 0; i < 4; i++){
            userServ.saveUser("Name" + i, "LastName" + i, (byte) (20 + i * 5));
            System.out.println("User с именем - " + "Name" + i + " добавлен в базу данных");
        }
        userServ.removeUserById(1);
        List<User> usList = userServ.getAllUsers();
        System.out.println(usList);
        userServ.cleanUsersTable();
        userServ.dropUsersTable();
        Util.closeConnection();
    }

}
