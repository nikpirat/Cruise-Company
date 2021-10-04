package dao.impl;

import dao.ConnectionFactory;
import dao.exception.DaoException;
import dao.ShipDao;
import model.Ship;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipDaoImpl implements ShipDao {
    private static final Logger log = Logger.getLogger(ShipDaoImpl.class);

    @Override
    public void create(Ship ship) {
        log.info("Enter create with parameters : " + ship);
        String sql = "INSERT INTO ship (name, passenger_amount, route_to, route_from, " +
                "amount_ports, travel_start, travel_end, standart_price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setString(1, ship.getName());
            insertStatement.setInt(2, ship.getPassengerAmount());
            insertStatement.setString(3, ship.getRouteTo());
            insertStatement.setString(4, ship.getRouteFrom());
            insertStatement.setInt(5, ship.getAmountPorts());
            insertStatement.setDate(6, ship.getTravelStart());
            insertStatement.setDate(7, ship.getTravelEnd());
            insertStatement.setFloat(8, ship.getPrice());
            insertStatement.execute();
        } catch (SQLException e) {
            log.error("Can`t create ship");
            throw new DaoException("Can`t create ship", e);
        }
        log.info("Exit method");
    }

    @Override
    public Ship getById(int id) {
        log.info("Enter getById with parameters : " + id);
        Ship ship = null;
        String sql = "Select * from ship WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setInt(1, id);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                while (resultSet.next()) {
                    ship = new Ship();
                    ship.setAmountPorts(resultSet.getInt("amount_ports"));
                    ship.setPassengerAmount(resultSet.getInt("passenger_amount"));
                    ship.setRouteFrom(resultSet.getString("route_from"));
                    ship.setName(resultSet.getString("name"));
                    ship.setRouteTo(resultSet.getString("route_to"));
                    ship.setTravelStart(resultSet.getDate("travel_start"));
                    ship.setTravelEnd(resultSet.getDate("travel_end"));
                    ship.setId(resultSet.getInt("id"));
                    ship.setPrice(resultSet.getFloat("standart_price"));
                }
            }

        } catch (SQLException e) {
            log.error("Can`t create ship");
            throw new DaoException("Can`t get ship", e);
        }
        log.info("Exit method with parameters : " + ship);
        return ship;
    }

    @Override
    public void update(Ship ship) {
        log.info("Enter update with parameters : " + ship);
        String sql = "UPDATE  ship set name, passenger_amount = ?, route_to = ?, route_from = ?,amount_ports = ?," +
                "travel_start = ?,travel_end = ?,standart_price = ?" +
                "where id = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                insertStatement.setString(1, ship.getName());
                insertStatement.setInt(2, ship.getPassengerAmount());
                insertStatement.setString(3, ship.getRouteTo());
                insertStatement.setString(4, ship.getRouteFrom());
                insertStatement.setInt(5, ship.getAmountPorts());
                insertStatement.setDate(6, ship.getTravelStart());
                insertStatement.setDate(7, ship.getTravelEnd());
                insertStatement.setFloat(8, ship.getPrice());
                insertStatement.setFloat(9, ship.getId());
                insertStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                log.error("Can`t update ship");
                throw new DaoException("Can`t update ship", e);
            }
        } catch (SQLException e) {
            log.error("Can`t update ship");
            throw new DaoException("Can`t update ship", e);
        }
        log.info("Exit method");
    }

    @Override
    public void deleteById(int id) {
        log.info("Enter delete method with id : " + id);
        String sql = "DELETE FROM ship WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error("Can`t delete ship");
            throw new DaoException("Can`t delete ship", e);
        }
        log.info("Exit method");
    }

    @Override
    public List<Ship> findAll() {
        log.info("Enter findAll");
        List<Ship> ships  = new ArrayList<>();
        String sql = "select * from ship";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Ship ship = new Ship();
                ship.setAmountPorts(resultSet.getInt("amount_ports"));
                ship.setPassengerAmount(resultSet.getInt("passenger_amount"));
                ship.setRouteFrom(resultSet.getString("route_from"));
                ship.setName(resultSet.getString("name"));
                ship.setRouteTo(resultSet.getString("route_to"));
                ship.setTravelStart(resultSet.getDate("travel_start"));
                ship.setTravelEnd(resultSet.getDate("travel_end"));
                ship.setId(resultSet.getInt("id"));
                ship.setPrice(resultSet.getFloat("standart_price"));
                ships.add(ship);
            }
        } catch (SQLException e) {
            log.error("Can`t find all ships");
            throw new DaoException("Can`t find all ships", e);
        }
        log.info("Exit method with parameters : " + ships);
        return ships;
    }

    public Ship getAllShipByUserId(int id) {
        log.info("Enter getAllShipByUserId with parameters : " + id);
        Ship ship = null;
        String sql = "select * from ship join user_role on ship.id = user_role.cruise_id where user_role.id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setInt(1, id);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                while (resultSet.next()) {
                    ship = new Ship();
                    ship.setAmountPorts(resultSet.getInt("amount_ports"));
                    ship.setPassengerAmount(resultSet.getInt("passenger_amount"));
                    ship.setRouteFrom(resultSet.getString("route_from"));
                    ship.setName(resultSet.getString("name"));
                    ship.setRouteTo(resultSet.getString("route_to"));
                    ship.setTravelStart(resultSet.getDate("travel_start"));
                    ship.setTravelEnd(resultSet.getDate("travel_end"));
                    ship.setPrice(resultSet.getFloat("standart_price"));
                }
            }
        } catch (SQLException e) {
            log.error("Can`t find all ships");
            throw new DaoException("Can`t get all ships by user id", e);
        }
        log.info("Exit method with parameters : " + ship);
        return ship;
    }
}
