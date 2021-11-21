package dao;

import model.CruiseInfo;
import model.Ship;

import java.util.List;

public interface CruiseInfoDao {
    CruiseInfo create(CruiseInfo cruiseInfo);
    CruiseInfo getById(int id);
    void update(CruiseInfo cruiseInfo);
    void deleteById(int id);
    List<CruiseInfo> findAll();
    List<CruiseInfo> getAllCruiseInfoByUserId(int id);
}
