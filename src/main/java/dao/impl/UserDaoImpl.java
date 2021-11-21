package dao.impl;

import dao.ConnectionFactory;
import dao.exception.DaoException;
import dao.UserDao;
import model.enums.Role;
import model.User;
import org.apache.log4j.Logger;
import сonstants.Constants;

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
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(Constants.SQL_INSERT_INTO_USER)) {
            insertIntoDB(user, insertStatement);
            insertStatement.execute();
        } catch (SQLException e) {
            log.error("Can`t create user");
            throw new DaoException("Can`t create user", e);
        }
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        User user = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(Constants.SQL_GET_BY_LOGIN_AND_PASS)) {
            insertStatement.setString(1, login);
            insertStatement.setString(2, password);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                if (resultSet.next())
                    user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            log.error("Can`t getByLoginAndPassword users");
            throw new DaoException("Can`t find by login and password user", e);
        }
        return user;
    }

    @Override
    public User getById(int id) {
        User user = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(Constants.SQL_FIND_USER)) {
            insertStatement.setInt(1, id);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                if (resultSet.next())
                    user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            log.error("Can`t get user");
            throw new DaoException("Can`t get user", e);
        }
        return user;
    }


    @Override
    public void update(User user) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement insertStatement = connection.prepareStatement(Constants.SQL_UPDATE_USER)) {
                insertIntoDB(user, insertStatement);
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
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SQL_DELETE_USER)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error("Can`t delete user");
            throw new DaoException("Can`t delete user", e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SQL_FIND_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user;
                user = extractUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Can`t find all user");
            throw new DaoException("Can`t find all users", e);
        }
        return users;
    }

    @Override
    public List<User> findUsersUsingLimitAndOffset(int currentPage, int recordsPerPage) {
        List<User> users = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SQL_FIND_USERS_USING_LIMIT_AND_OFFSET)) {
            statement.setInt(1, recordsPerPage);
            statement.setInt(2, start);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user;
                user = extractUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Can`t find user with filters");
            throw new DaoException("Can`t find user with filters", e);
        }
        return users;
    }

    @Override
    public int getNumberOfRows() {
        log.info("Enter getNumberOfRows");
        int numOfRows = 0;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SQL_GET_NUMBER_OF_ROWS)) {
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

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setId(resultSet.getInt("id"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setBalance(resultSet.getFloat("balance"));
        return user;
    }

    private void insertIntoDB(User user, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setString(1, user.getLogin());
        insertStatement.setString(2, user.getPassword());
        insertStatement.setString(3, Role.USER.toString());
        insertStatement.setString(4, user.getName());
        insertStatement.setString(5, user.getSurname());
        insertStatement.setFloat(6, user.getBalance());
    }
}
