package dao.impl;

import dao.ConnectionFactory;
import dao.exception.DaoException;
import dao.ExcursionDao;
import model.CruiseInfo;
import model.Excursion;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDaoImpl implements ExcursionDao {
    private static final Logger log = Logger.getLogger(ExcursionDaoImpl.class);

    @Override
    public void create(Excursion excursion) {
        log.info("Enter create with parameters : " + excursion);
        String sql = "INSERT INTO excursion (name, duration, price, additional_info, ship_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setString(1, excursion.getName());
            insertStatement.setString(2, excursion.getDuration());
            insertStatement.setFloat(3, excursion.getPrice());
            insertStatement.setString(4, excursion.getAdditionalInfo());
            insertStatement.setInt(5, excursion.getShipId());
            insertStatement.execute();
        } catch (SQLException e) {
            log.error("Can`t create excursion");
            throw new DaoException("Can`t create excursion", e);
        }
        log.info("Exit method");
    }

    @Override
    public void update(Excursion excursion) {
        log.info("Enter update with parameters : " + excursion);
        String sql = "UPDATE  excursion set name, duration = ?, price = ?, additional_info = ?, ship_id = ? " +
                "where id = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                insertStatement.setString(1, excursion.getName());
                insertStatement.setString(2, excursion.getDuration());
                insertStatement.setFloat(3, excursion.getPrice());
                insertStatement.setString(4, excursion.getAdditionalInfo());
                insertStatement.setInt(5, excursion.getShipId());
                insertStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                log.error("Can`t create excursion");
                connection.rollback();
                throw new DaoException("Can`t update excursion", e);
            }
        } catch (SQLException e) {
            log.error("Can`t update excursion");
            throw new DaoException("Can`t update excursion", e);
        }
        log.info("Exit method");
    }

    @Override
    public void deleteById(int id) {
        log.info("Enter delete method with id : " + id);
        String sql = "DELETE FROM excursion WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error("Can`t delete excursion");
            throw new DaoException("Can`t delete excursion", e);
        }
        log.info("Exit method");
    }

    @Override
    public List<Excursion> findAll() {
        log.info("Enter delete method");
        List<Excursion> excursions = new ArrayList<>();
        String sql = "select * from excursion";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Excursion excursion = new Excursion();
                excursion.setId(resultSet.getInt("id"));
                excursion.setName(resultSet.getString("name"));
                excursion.setDuration(resultSet.getString("duration"));
                excursion.setAdditionalInfo(resultSet.getString("additional_info"));
                excursion.setPrice(resultSet.getFloat("price"));
                excursion.setShipId(resultSet.getInt("ship_id"));
                excursions.add(excursion);
            }
        } catch (SQLException e) {
            log.error("Can`t find all excursions");
            throw new DaoException("Can`t find all excursions", e);
        }
        log.info("Exit method with : " + excursions);
        return excursions;
    }

    @Override
    public Excursion getById(int id) {
        log.info("Enter with parameters : " + id);
        Excursion excursion = null;
        String sql = "Select * from excursion WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setInt(1, id);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                while (resultSet.next()) {
                    excursion = new Excursion();
                    excursion.setId(resultSet.getInt("id"));
                    excursion.setShipId(resultSet.getInt("ship_id"));
                    excursion.setPrice(resultSet.getFloat("price"));
                    excursion.setAdditionalInfo(resultSet.getString("additional_info"));
                    excursion.setDuration(resultSet.getString("duration"));
                    excursion.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            log.error("Can`t get excursion");
            throw new DaoException("Can`t get excursion", e);
        }
        log.info("Exit method with : " + excursion);
        return excursion;
    }
}
