package test;

import dao.ExcursionDao;
import dao.ShipDao;
import model.Excursion;
import model.Ship;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Test class for testing ExcursionDao
 * 2 methods : getById and findAll
 * */
public class ExcursionDaoImplTest {

    @Test
    public void testFindById() {
        ExcursionDao excursionDao = mock(ExcursionDao.class);
        Excursion excursion = new Excursion();
        excursion.setName("name");
        when(excursionDao.getById(1)).thenReturn(excursion);
        assertEquals(excursionDao.getById(1), excursion);
    }

    @Test
    public void testfindAllShips() {
        ExcursionDao excursionDao = mock(ExcursionDao.class);
        Excursion excursion = new Excursion();
        Excursion excursion1 = new Excursion();
        List<Excursion> excursions = new ArrayList<>();
        excursion.setName("First");
        excursion1.setName("Second");
        excursions.add(excursion);
        excursions.add(excursion1);

        when(excursionDao.findAll()).thenReturn(excursions);
        assertEquals(excursionDao.findAll(), excursions);
    }



}