package test;




import dao.BonusDao;
import dao.impl.BonusDaoImpl;
import model.Bonus;
import model.enums.RoomType;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for testing BonusDao
 * Several methods : getById and findAll with different data
 * */
public class BonusDaoImplTest {

    @Test
    public void testGetByIdWithOneObject() {
        BonusDao bonusDao = mock(BonusDao.class);
        Bonus bonus = new Bonus();
        bonus.setId(1);
        bonus.setName("name");
        when(bonusDao.getById(1)).thenReturn(bonus);
        assertEquals(bonusDao.getById(1), bonus);
    }

    @Test
    public void testGetByIdWithSeveralObjects() {
        BonusDao bonusDao = mock(BonusDao.class);
        Bonus bonus = new Bonus();
        bonus.setId(1);
        bonus.setName("name");

        Bonus bonusSecond = new Bonus();
        bonusSecond.setName("src/main/test");
        bonusSecond.setId(1);
        when(bonusDao.getById(1)).thenReturn(bonus);
        assertFalse(bonusDao.getById(1).equals(bonusSecond));
    }

    @Test
    public void testGetAll() {
        BonusDao bonusDao = mock(BonusDaoImpl.class);
        Bonus bonus1 = new Bonus();
        bonus1.setName("bonus1");
        bonus1.setRoomType(RoomType.PRESIDENT);
        Bonus bonus2 = new Bonus();
        bonus2.setName("bonus2");
        bonus2.setRoomType(RoomType.PRESIDENT);
        Bonus bonus3 = new Bonus();
        bonus3.setName("bonus3");
        bonus3.setRoomType(RoomType.PRESIDENT);
        ArrayList<Bonus> bonuses = new ArrayList<>();

        bonuses.add(bonus1);
        bonuses.add(bonus2);
        bonuses.add(bonus3);

        when(bonusDao.findAllByRoomType(RoomType.PRESIDENT)).thenReturn(bonuses);
        assertEquals(bonusDao.findAllByRoomType(RoomType.PRESIDENT),bonuses);
    }

}