package dao;

import model.CruiseInfo;
import model.Ship;

import java.util.List;

public interface CruiseInfoDao {
    public CruiseInfo create(CruiseInfo cruiseInfo);
    public CruiseInfo getById(int id);
    public void update(CruiseInfo cruiseInfo);
    public void deleteById(int id);
    public List<CruiseInfo> findAll();
}
