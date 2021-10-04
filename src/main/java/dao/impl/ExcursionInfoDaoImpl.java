package dao.impl;

import dao.ConnectionFactory;
import dao.exception.DaoException;
import dao.ExcursionInfoDao;
import model.Excursion;
import model.ExcursionInfo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExcursionInfoDaoImpl implements ExcursionInfoDao {
    private static final Logger log = Logger.getLogger(ExcursionInfoDaoImpl.class);

    @Override
    public ExcursionInfo create(ExcursionInfo excursionInfo) {
        log.info("Enter create with parameters : " + excursionInfo);
        String sql = "INSERT INTO excursion_info (user_id, excursion_id, cruise_info_id) " +
                "VALUES (?, ?, ?)";
        ExcursionInfo createdExcursionInfo = new ExcursionInfo();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, excursionInfo.getUserId());
            insertStatement.setInt(2, excursionInfo.getExcursionId());
            insertStatement.setInt(3, excursionInfo.getCruiseInfoId());
            insertStatement.execute();
            try (ResultSet resultSet = insertStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    createdExcursionInfo.setId(resultSet.getInt("id"));
                    createdExcursionInfo.setExcursionId(resultSet.getInt("excursion_id"));
                    createdExcursionInfo.setUserId(resultSet.getInt("user_id"));
                    createdExcursionInfo.setCruiseInfoId(resultSet.getInt("cruise_info_id"));
                }
            }
        } catch (SQLException e) {
            log.error("Can`t create excursion Info");
            throw new DaoException("Can`t create excursionInfo", e);
        }
        log.info("Exit method with parameters : " + createdExcursionInfo);
        return createdExcursionInfo;
    }

    @Override
    public void update(ExcursionInfo excursionInfo) {
        log.info("Enter update with parameters : " + excursionInfo);
        String sql = "UPDATE  excursion_info set user_id, excursion_id = ?, cruise_info_id = ?, " +
                "where id = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                insertStatement.setInt(1, excursionInfo.getUserId());
                insertStatement.setInt(2, excursionInfo.getExcursionId());
                insertStatement.setInt(3, excursionInfo.getCruiseInfoId());
                insertStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                log.error("Can`t update excursion Info");
                throw new DaoException("Can`t update excursionInfo", e);
            }
        } catch (SQLException e) {
            log.error("Can`t update excursion Info");
            throw new DaoException("Can`t update excursionInfo", e);
        }
    }

    @Override
    public void deleteById(int id) {
        log.info("Enter delete method with id : " + id);
        String sql = "DELETE FROM excursion_info WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error("Can`t delete excursion Info");
            throw new DaoException("Can`t delete excursionInfo", e);
        }
    }

    @Override
    public List<ExcursionInfo> findAll() {
        log.info("Enter findAll ");
        List<ExcursionInfo> excursionInfos = new ArrayList<>();
        String sql = "select * from excursion_info";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ExcursionInfo excursionInfo = new ExcursionInfo();
                excursionInfo.setId(resultSet.getInt("id"));
                excursionInfo.setUserId(resultSet.getInt("user_id"));
                excursionInfo.setExcursionId(resultSet.getInt("excursion_id"));
                excursionInfo.setCruiseInfoId(resultSet.getInt("cruise_info_id"));
                excursionInfos.add(excursionInfo);
            }
        } catch (SQLException e) {
            log.error("Can`t find all excursion Info");
            throw new DaoException("Can`t find all excursionInfo", e);
        }
        log.info("Exit method with parameters : " + excursionInfos);
        return excursionInfos;
    }

    @Override
    public List<ExcursionInfo> findAllByUserId(int id) {
        log.info("Enter findAll with parameters : " + id);
        List<ExcursionInfo> excursionInfos = new ArrayList<>();
        String sql = "select * from excursion_info where user_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setInt(1, id);
             ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ExcursionInfo excursionInfo = new ExcursionInfo();
                excursionInfo.setId(resultSet.getInt("id"));
                excursionInfo.setUserId(resultSet.getInt("user_id"));
                excursionInfo.setExcursionId(resultSet.getInt("excursion_id"));
                excursionInfo.setCruiseInfoId(resultSet.getInt("cruise_info_id"));
                excursionInfos.add(excursionInfo);
            }
        } catch (SQLException e) {
            log.error("Can`t find all excursion Info");
            throw new DaoException("Can`t find all excursionInfo by user info", e);
        }
        log.info("Exit method with parameters : " + excursionInfos);
        return excursionInfos;
    }
}
