package dao;

import model.Ship;

import java.util.List;

public interface ShipDao {
    void create(Ship ship);
    Ship getById(int id);
    void update(Ship ship);
    void deleteById(int id);
    List<Ship> findAll();
}
