package dao.impl;

import dao.ConnectionPool;
import dao.exception.DaoException;
import dao.ShipDao;
import model.Ship;
import org.apache.log4j.Logger;
import сonstants.Constants;

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
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_INSERT_INTO_SHIP)) {
            insertIntoDB(ship, ps);
            ps.execute();
        } catch (SQLException e) {
            log.error("Can`t create ship");
            throw new DaoException("Can`t create ship", e);
        }
    }

    @Override
    public Ship getById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ship ship = null;
        try {
            ps= ConnectionPool.getConnection().prepareStatement(Constants.SQL_FIND_SHIP);
            ps.setInt(1, id);
             rs = ps.executeQuery();
                if (rs.next())
                    ship=extractShip(rs);
        } catch (SQLException e) {
            log.error("Can`t create ship");
            throw new DaoException("Can`t get ship", e);
        }finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(rs);
        }
        return ship;
    }

    @Override
    public void update(Ship ship) {
        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement insertStatement = connection.prepareStatement(Constants.SQL_UPDATE_SHIP)) {
                insertIntoDB(ship, insertStatement);
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
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SQL_DELETE_SHIP)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error("Can`t delete ship");
            throw new DaoException("Can`t delete ship", e);
        }
    }

    @Override
    public List<Ship> findAll() {

        List<Ship> ships  = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SQL_FIND_ALL_SHIPS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Ship ship;
                ship=extractShip(resultSet);
                ships.add(ship);
            }
        } catch (SQLException e) {
            log.error("Can`t find all ships");
            throw new DaoException("Can`t find all ships", e);
        }
        return ships;
    }
    private Ship extractShip(ResultSet resultSet) throws SQLException {
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
        return ship;
    }

    private void insertIntoDB(Ship ship, PreparedStatement ps) throws SQLException {
        ps.setString(1, ship.getName());
        ps.setInt(2, ship.getPassengerAmount());
        ps.setString(3, ship.getRouteTo());
        ps.setString(4, ship.getRouteFrom());
        ps.setInt(5, ship.getAmountPorts());
        ps.setDate(6, ship.getTravelStart());
        ps.setDate(7, ship.getTravelEnd());
        ps.setFloat(8, ship.getPrice());
    }
}
