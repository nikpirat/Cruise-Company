package service;

import dao.ShipDao;
import dao.impl.ShipDaoImpl;
import model.Ship;

import java.util.List;

public class ShipService {

    private final ShipDao shipDao = new ShipDaoImpl();

    public void create(Ship ship){shipDao.create(ship);}
    public Ship getById(int id){return shipDao.getById(id);}
    public void update(Ship ship){shipDao.update(ship);}
    public void deleteById(int id){shipDao.deleteById(id);}
    public List<Ship> findAll(){return shipDao.findAll();}
}
