package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
    // Методы создания и удаления таблицы пользователей
    // в классе UserHibernateDaoImpl должны быть реализованы с помощью SQL.
        //Connection connection = Util.getConnection();
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(80),
                lastname VARCHAR(80),
                age TINYINT(100));
                """;
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

            System.out.println("Таблица users создана");

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            he.printStackTrace();

        }
    }

    @Override
    public void dropUsersTable() {
    // Методы создания и удаления таблицы пользователей
    // в классе UserHibernateDaoImpl должны быть реализованы с помощью SQL.

        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица users удалена");
        } catch (HibernateException he) {
                if(transaction != null) {
                    transaction.rollback();
                }
            he.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null){
                transaction.rollback();
            }
            he.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null){
                transaction.rollback();
            }
            he.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> userList = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            userList = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null){
                transaction.rollback();
            }
            he.printStackTrace();
        }
        System.out.println(userList);
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (HibernateException he){
            if (transaction != null){
                transaction.rollback();
            }
            he.printStackTrace();
        }
     }

}
