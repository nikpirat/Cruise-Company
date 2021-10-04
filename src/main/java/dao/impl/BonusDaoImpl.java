package dao.impl;

import dao.BonusDao;
import dao.ConnectionFactory;
import dao.exception.DaoException;
import model.*;
import model.enums.RoomType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BonusDaoImpl implements BonusDao {
    private static final Logger log = Logger.getLogger(BonusDao.class);

    @Override
    public void create(Bonus bonus) {
        log.info("Enter create with parameters : " + bonus);
        String sql = "INSERT INTO bonus (name, description) VALUES (?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bonus.getName());
            log.info("Set bonus name : " + bonus.getName());
            statement.setString(2, bonus.getDescription());
            log.info("Set bonus description : " + bonus.getDescription());
            statement.execute();
        } catch (SQLException e) {
            log.error("Error creating bonus");
            throw new DaoException("Can`t create bonus", e);
        }
        log.info("Exit create method");
    }

    @Override
    public void update(Bonus bonus) {
        log.info("enter update with params : " + bonus);
        String sql = "UPDATE  bonus set name = ?, description = ?, room_type = ? where id = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                insertStatement.setString(1, bonus.getName());
                log.info("Set name : " + bonus.getName());
                insertStatement.setString(2, bonus.getDescription());
                log.info("Set description : " + bonus.getDescription());
                insertStatement.setString(3, bonus.getRoomType().toString());
                log.info("Set room type : " + bonus.getRoomType());
                insertStatement.setInt(4, bonus.getId());
                log.info("Set id : " + bonus.getId());
                insertStatement.executeUpdate();
                connection.setAutoCommit(false);
                connection.commit();
                connection.setAutoCommit(true);
                log.info("Updated");
            } catch (SQLException e) {
                connection.rollback();
                log.error("Can`t update bonus");
                throw new DaoException("Can`t update bonus", e);
            }
        } catch (SQLException e) {
            log.error("Can`t update bonus");
            throw new DaoException("Can`t update bonus", e);
        }
        log.info("Exit update");
    }

    @Override
    public void deleteById(int id) {
        log.info("Enter delete method with id : " + id);
        String sql = "DELETE FROM bonus WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            log.info("Set id : " + id);
            statement.execute();
        } catch (SQLException e) {
            log.error("Can`t delete bonus");
            throw new DaoException("Can`t delete bonus", e);
        }
        log.info("Exit delete method");
    }


    @Override
    public List<Bonus> findAllWithRoomTypeNull() {
        log.info("Enter findAllWithRoomTypeNull()");
        List<Bonus> userBonuses = new ArrayList<>();
        String sql = "select * from bonus WHERE room_type is NULL";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bonus bonus = new Bonus();
                bonus.setId(resultSet.getInt("id"));
                log.info("Set id :" + resultSet.getInt("id"));
                bonus.setName(resultSet.getString("name"));
                log.info("Set name :" + resultSet.getString("name"));
                bonus.setDescription(resultSet.getString("description"));
                log.info("Set description :" + resultSet.getString("description"));
                userBonuses.add(bonus);
            }
        } catch (SQLException e) {
            log.error("Cant find");
            throw new DaoException("Can`t find all bonuses", e);
        }
        log.info("Exit findAllWithRoomTypeNull() with list : " + userBonuses);
        return userBonuses;
    }

    @Override
    public List<Bonus> findAllByRoomType(RoomType roomType) {
        log.info("Enter findAllByRoomType() with type : " + roomType);
        List<Bonus> userBonuses = new ArrayList<>();
        String sql = "select * from bonus WHERE room_type = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, roomType.toString());
            log.info("Set type : " + roomType.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bonus bonus = new Bonus();
                bonus.setId(resultSet.getInt("id"));
                log.info("Set id :" + resultSet.getInt("id"));
                bonus.setName(resultSet.getString("name"));
                log.info("Set name :" + resultSet.getString("name"));
                bonus.setDescription(resultSet.getString("description"));
                log.info("Set description :" + resultSet.getString("description"));
                bonus.setRoomType(RoomType.valueOf(resultSet.getString("room_type")));
                log.info("Set room type :" + resultSet.getString("room_type"));
                userBonuses.add(bonus);
            }
        } catch (SQLException e) {
            log.error("Cant find");
            throw new DaoException("Can`t find all bonuses by room type", e);
        }
        log.info("Exit findAllByRoomType() with list : " + userBonuses);
        return userBonuses;
    }


    @Override
    public Bonus getById(int id) {
        log.info("Enter getById() with id : " + id);
        Bonus bonus = null;
        String sql = "SELECT * FROM bonus WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setInt(1, id);
            log.info("set id " + id);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                while (resultSet.next()) {
                    bonus = new Bonus();
                    bonus.setId(resultSet.getInt("id"));
                    log.info("Set id :" + resultSet.getInt("id"));
                    bonus.setName(resultSet.getString("name"));
                    log.info("Set name :" + resultSet.getString("name"));
                    bonus.setDescription(resultSet.getString("description"));
                    log.info("Set description :" + resultSet.getString("description"));
                    if (resultSet.getString("room_type") != null) {
                        bonus.setRoomType(RoomType.valueOf(resultSet.getString("room_type")));
                    }
                    log.info("Set room type :" + resultSet.getString("room_type"));
                }
            }
        } catch (SQLException e) {
            log.error("Cant get bonus");
            throw new DaoException("Can`t get bonus", e);
        }
        log.info("Exit getById() with bonus : " + bonus);
        return bonus;
    }
}
