package test;

import dao.UserDao;
import model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Test class for testing UserDao
 * 2 methods : getById and findAll
 * */
public class UserDaoImplTest {

    @Test
    public void testFindById() {
        UserDao userDao = mock(UserDao.class);
        User user = new User();
        user.setId(1);
        user.setName("Ivan");
        user.setLogin("login");
        user.setPassword("password");
        user.setSurname("Ivanov");
        when(userDao.getByLoginAndPassword(user.getLogin(), user.getPassword())).thenReturn(user);
        assertEquals(userDao.getByLoginAndPassword(user.getLogin(), user.getPassword()), user);
    }

    @Test
    public void testfindAllUsers() {
        UserDao userDao = mock(UserDao.class);
        List<User> users = new ArrayList<>();
        User user = new User();
        User user2 = new User();
        user.setId(1);
        user.setName("Ivan");
        users.add(user);
        user2.setId(2);
        user2.setName("Petro");
        users.add(user2);
        when(userDao.findAll()).thenReturn(users);
        assertEquals(userDao.findAll(), users);
    }

}