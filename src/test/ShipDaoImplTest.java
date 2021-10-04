package test;

import dao.ShipDao;
import model.Ship;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Test class for testing ShipDao
 * Several methods : getById and findAll with different data
 * */
public class ShipDaoImplTest {

    @Test
    public void testFindById() {
        ShipDao shipDao = mock(ShipDao.class);
        Ship ship = new Ship();
        ship.setId(1);
        ship.setName("Ship");
        when(shipDao.getById(1)).thenReturn(ship);
        assertEquals(shipDao.getById(1), ship);
    }

    @Test
    public void testfindAllShips() {
        ShipDao shipDao = mock(ShipDao.class);
        List<Ship> ships = new ArrayList<>();
        Ship ship = new Ship();
        ship.setName("name");
        Ship ship1 = new Ship();
        ship1.setName("Name2");

        when(shipDao.findAll()).thenReturn(ships);
        assertEquals(shipDao.findAll(), ships);
    }

}