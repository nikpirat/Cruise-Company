package dao.impl;

import dao.ConnectionFactory;
import dao.exception.DaoException;
import dao.UserDao;
import model.enums.Role;
import model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger log = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void create(User user) {
        log.info("Enter create with parameters : " + user);
        String sql = "INSERT INTO user_role (login, password, role, name, surname, balance) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setString(1, user.getLogin());
            insertStatement.setString(2, user.getPassword());
            insertStatement.setString(3, Role.USER.toString());
            insertStatement.setString(4, user.getName());
            insertStatement.setString(5, user.getSurname());
            insertStatement.setFloat(6, user.getBalance());
            insertStatement.execute();
        } catch (SQLException e) {
            log.error("Can`t create user");
            throw new DaoException("Can`t create user", e);
        }
        log.info("Exit create");
    }


    public User getByLoginAndPassword(String login, String password) {
        log.info("Enter getByLoginAndPassword with parameters : " + login + " " + password);
        User user = null;
        String sql = "Select * from user_role as u where u.login = ? and u.password = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setString(1, login);
            insertStatement.setString(2, password);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User();
                    user.setLogin(resultSet.getString("login"));
                    user.setId(resultSet.getInt("id"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Role.valueOf(resultSet.getString("role")));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setBalance(resultSet.getFloat("balance"));
                }
            }
        } catch (SQLException e) {
            log.error("Can`t getByLoginAndPassword users");
            throw new DaoException("Can`t find by login and password user", e);
        }
        log.info("Exit method with parametes : " + user);
        return user;
    }


    public User getById(int id) {
        log.info("Enter getById with parameters : " + id);
        User user = null;
        String sql = "Select * from user_role WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setInt(1, id);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User();
                    user.setLogin(resultSet.getString("login"));
                    user.setId(resultSet.getInt("id"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Role.valueOf(resultSet.getString("role")));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setBalance(resultSet.getFloat("balance"));
                }
            }
        } catch (SQLException e) {
            log.error("Can`t get user");
            throw new DaoException("Can`t get user", e);
        }
        log.info("Exit method with parametes : " + user);
        return user;
    }


    @Override
    public void update(User user) {
        log.info("Enter update with parameters : " + user);
        String sql = "UPDATE  user_role set login = ?, password = ?, role = ?, name = ? , surname = ?, balance = ?  " +
                "where id = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                insertStatement.setString(1, user.getLogin());
                insertStatement.setString(2, user.getPassword());
                insertStatement.setString(3, Role.USER.toString());
                insertStatement.setString(4, user.getName());
                insertStatement.setString(5, user.getSurname());
                insertStatement.setFloat(6, user.getBalance());
                insertStatement.setInt(7, user.getId());
                insertStatement.executeUpdate();
                connection.setAutoCommit(false);
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                connection.rollback();
                log.error("Can`t update user");
                throw new DaoException("Can`t update user", e);
            }
        } catch (SQLException e) {
            log.error("Can`t update user");
            throw new DaoException("Can`t update user", e);
        }
        log.info("Exit update");
    }

    @Override
    public void deleteById(int id) {
        log.info("Enter delete method with id : " + id);
        String sql = "DELETE FROM user_role WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error("Can`t delete user");
            throw new DaoException("Can`t delete user", e);
        }
        log.info("Exit delete");
    }

    @Override
    public List<User> findAll() {
        log.info("Enter findall");
        List<User> users = new ArrayList<>();
        String sql = "select * from user_role";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setId(resultSet.getInt("id"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setBalance(resultSet.getFloat("balance"));
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Can`t find all user");
            throw new DaoException("Can`t find all users", e);
        }
        log.info("Exit method with parameters : " + users);
        return users;
    }

    @Override
    public List<User> findUsersUsingLimitAndOffset(int currentPage, int recordsPerPage) {
        log.info("Enter findUsersUsingLimitAndOffset  : " + currentPage + " " + recordsPerPage);
        List<User> users = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        String sql = "select * from user_role ORDER BY ID LIMIT ? OFFSET ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, recordsPerPage);
            statement.setInt(2, start);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setId(resultSet.getInt("id"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setBalance(resultSet.getFloat("balance"));
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Can`t Can`t find user with filters");
            throw new DaoException("Can`t find user with filters", e);
        }
        log.info("Exit method with parameters : " + users);
        return users;
    }

    @Override
    public int getNumberOfRows() {
        log.info("Enter getNumberOfRows");
        int numOfRows = 0;
        String sql = "select COUNT(id) FROM user_role";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numOfRows = resultSet.getInt("count(id)");
            }
        } catch (SQLException e) {
            log.error("Can`t get numbers of rows user");
            throw new DaoException("Can`t get numbers of rows user", e);
        }
        log.info("Exit method with parameters : " + numOfRows);
        return numOfRows;
    }
}
