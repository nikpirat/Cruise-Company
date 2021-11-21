package dao.impl;

import dao.ConnectionPool;
import dao.CruiseInfoDao;
import dao.exception.DaoException;
import model.CruiseInfo;
import model.enums.RoomType;
import org.apache.log4j.Logger;
import сonstants.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CruiseInfoDaoImpl implements CruiseInfoDao {
    private static final Logger log = Logger.getLogger(CruiseInfo.class);

    @Override
    public CruiseInfo create(CruiseInfo cruiseInfo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConnectionPool.getConnection().prepareStatement(Constants.SQL_INSERT_INTO_CRUISE_INFO, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cruiseInfo.getShipId());
            ps.setString(2, cruiseInfo.getRoomType().toString());
            ps.setDouble(3, cruiseInfo.getTotalPrice());
            ps.setInt(4, cruiseInfo.getUserId());

            if (ps.executeUpdate() != 1) return null;
            rs = ps.getGeneratedKeys();

            if (rs.next())
                cruiseInfo.setId(rs.getInt(1));
        } catch (SQLException e) {
            log.error("Can`t create cruise info");
            throw new DaoException("Can`t create cruiseInfo", e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(rs);
        }
        log.info("Exit create");
        return cruiseInfo;
    }

    @Override
    public CruiseInfo getById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        CruiseInfo cruiseInfo = null;
        try {
            ps = ConnectionPool.getConnection().prepareStatement(Constants.SQL_FIND_CRUISE_INFO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next())
                cruiseInfo = extractCruiseInfo(rs);
        } catch (SQLException e) {
            log.error("Can`t get cruise info");
            throw new DaoException("Can`t get cruiseInfo", e);
        }finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(rs);
        }
        log.info("Exit get with : " + cruiseInfo);
        return cruiseInfo;
    }

    @Override
    public void update(CruiseInfo cruiseInfo) {
        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(Constants.SQL_UPDATE_CRUISE_INFO)) {
                ps.setInt(1, cruiseInfo.getShipId());
                ps.setString(2, cruiseInfo.getRoomType().toString());
                ps.setDouble(3, cruiseInfo.getTotalPrice());
                ps.setInt(4, cruiseInfo.getUserId());
                ps.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                log.error("Cant update");
                throw new DaoException("Can`t update cruiseInfo", e);
            }
        } catch (SQLException e) {
            log.error("Cant update");
            throw new DaoException("Can`t update cruiseInfo", e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SQL_DELETE_CRUISE_INFO)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error("Cant delete");
            throw new DaoException("Can`t delete cruiseInfo", e);
        }
    }

    @Override
    public List<CruiseInfo> findAll() {
        List<CruiseInfo> allCruisesInfo = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_FIND_ALL_CRUISE_INFO);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CruiseInfo cruiseInfo;
                cruiseInfo=extractCruiseInfo(rs);
                allCruisesInfo.add(cruiseInfo);
            }
        } catch (SQLException e) {
            log.error("Error");
            throw new DaoException("Can`t get all cruiseInfo", e);
        }
        return allCruisesInfo;
    }
    @Override
    public List<CruiseInfo> getAllCruiseInfoByUserId(int id) {
        List<CruiseInfo> cruiseInfoList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SQL_FIND_ALL_CRUISE_INFO_BY_USER_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CruiseInfo cruiseInfo;
                    cruiseInfo = extractCruiseInfo(rs);

                    cruiseInfoList.add(cruiseInfo);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can`t get all cruiseInfo by user id", e);
        }
        return cruiseInfoList;
    }

    private CruiseInfo extractCruiseInfo(ResultSet resultSet) throws SQLException {
        CruiseInfo cruiseInfo = new CruiseInfo();
        cruiseInfo.setId(resultSet.getInt(1));
        cruiseInfo.setShipId(resultSet.getInt(2));
        cruiseInfo.setRoomType(RoomType.valueOf(resultSet.getString(3)));
        cruiseInfo.setTotalPrice(resultSet.getFloat(4));
        cruiseInfo.setUserId(resultSet.getInt(5));
        return cruiseInfo;
    }
}
