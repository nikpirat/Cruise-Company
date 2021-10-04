package test;


import dao.ExcursionInfoDao;
import model.ExcursionInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Test class for testing ExcursionInfo
 * Several methods : getById and findAll and findAllWithUserId  where uses user id as a condition
 * */

public class ExcursionInfoDaoImplTest {

    @Test
    public void testCreatingExcursionInfo() {
        ExcursionInfoDao excursionInfoDao = mock(ExcursionInfoDao.class);
        ExcursionInfo excursionInfo = new ExcursionInfo();
        excursionInfo.setUserId(1);
        excursionInfo.setId(1);
        when(excursionInfoDao.create(excursionInfo)).thenReturn(excursionInfo);
        assertEquals(excursionInfoDao.create(excursionInfo), excursionInfo);
    }

    @Test
    public void testfindAll() {
        List<ExcursionInfo> excursionInfos = new ArrayList<>();
        ExcursionInfoDao excursionInfoDao = mock(ExcursionInfoDao.class);
        ExcursionInfo excursionInfo = new ExcursionInfo();
        excursionInfo.setId(1);
        ExcursionInfo excursionInfo1 = new ExcursionInfo();
        excursionInfo1.setId(2);

        when(excursionInfoDao.findAll()).thenReturn(excursionInfos);
        assertEquals(excursionInfoDao.findAll(), excursionInfos);

    }

    @Test
    public void testfindAllByUserId() {
        List<ExcursionInfo> excursionInfos = new ArrayList<>();
        ExcursionInfoDao excursionInfoDao = mock(ExcursionInfoDao.class);
        ExcursionInfo excursionInfo = new ExcursionInfo();
        excursionInfo.setId(1);
        excursionInfo.setUserId(1);
        ExcursionInfo excursionInfo1 = new ExcursionInfo();
        excursionInfo1.setId(2);
        excursionInfo.setUserId(1);

        when(excursionInfoDao.findAllByUserId(1)).thenReturn(excursionInfos);
        assertEquals(excursionInfoDao.findAllByUserId(1), excursionInfos);

    }
}