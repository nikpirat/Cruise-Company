package service;

import dao.CruiseInfoDao;
import dao.impl.CruiseInfoDaoImpl;
import model.CruiseInfo;

import java.util.List;

public class CruiseInfoService {

    private final CruiseInfoDao cruiseInfoDao = new CruiseInfoDaoImpl();

    public CruiseInfo create(CruiseInfo cruiseInfo){return cruiseInfoDao.create(cruiseInfo);}
    public CruiseInfo getById(int id){return cruiseInfoDao.getById(id);}
    public void update(CruiseInfo cruiseInfo){cruiseInfoDao.update(cruiseInfo);}
    public void deleteById(int id){cruiseInfoDao.deleteById(id);}
    public List<CruiseInfo> findAll(){return cruiseInfoDao.findAll();}
    public List<CruiseInfo> getAllCruiseInfoByUserId(int id){return cruiseInfoDao.getAllCruiseInfoByUserId(id);}
}
