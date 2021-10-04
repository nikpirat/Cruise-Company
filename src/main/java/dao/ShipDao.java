package dao;

import model.Ship;

import java.util.List;

public interface ShipDao {
    public void create(Ship ship);
    public Ship getById(int id);
    public void update(Ship ship);
    public void deleteById(int id);
    public List<Ship> findAll();
}
