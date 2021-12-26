package test;


import dao.UserDao;
import model.User;
import model.enums.Role;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.SecurityService;
import service.UserService;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Test class for testing Validate
 * Several methods :check User. Method return true if will be found user
 * test login : check if login exist
 * test login with null : check with null value
 * test login with numbers check with number values
 * */

public class SecurityServiceTest {

    @Test
    public void testCheckUser() {
        SecurityService securityService = mock(SecurityService.class);

        String login = "login";
        String password = "password";

        when(securityService.checkUser(login, password)).thenReturn(true);
        assertTrue(securityService.checkUser(login, password));
    }
//
//    @Test
//    public void testCheckLogin() {
//        SecurityService securityService = mock(SecurityService.class);
//
//        String login = "login";
//
//        when(securityService.checkLogin(login)).thenReturn(true);
//        assertTrue(securityService.checkLogin(login));
//    }
//
//    @Test
//    public void testCheckLoginWithNullLogin() {
//        SecurityService securityService = mock(SecurityService.class);
//
//        when(securityService.checkLogin(null)).thenReturn(false);
//        assertFalse(securityService.checkLogin(null));
//    }
//    @Test
//    public void testCheckLoginWithNumbers() {
//        SecurityService securityService = mock(SecurityService.class);
//
//        String login = "123431";
//
//        when(securityService.checkLogin(login)).thenReturn(true);
//        assertTrue(securityService.checkLogin(login));
//    }

    private final static String LOGIN = "testName";
    private final static String PASSWORD = "testPassword";
    private final static String FIRST_NAME = "testEmail@gmail.com";
    private final static String SECOND_NAME = "testPhone";

    @InjectMocks
    private UserService userService;
    private User user;
    @Mock
    private SecurityService securityService;


//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        user = new User();
//        user.setLogin(LOGIN);
//        user.setName(FIRST_NAME);
//        user.setPassword(PASSWORD);
//        user.setSurname(SECOND_NAME);
//        user.setRole(Role.USER);
//        when(securityService.checkLogin(LOGIN)).thenReturn(Optional.ofNullable(user));
//    }
//
//    @Test
//    public void registrationUser() {
//        Boolean result = userService.create(new User(LOGIN,PASSWORD, FIRST_NAME, SECOND_NAME));
//
//        assertNotNull(result);
//        assertEquals(Boolean.TRUE, result);
//    }
//
//    @Test
//    public void loginUser() {
//        User result = userService.loginUser(LOGIN);
//
//        assertEquals(user, result.get());
//        assertEquals(user.getName(), result.get().getName());
//        assertEquals(user.getEmail(), result.get().getEmail());
//        assertEquals(user.getPassword(), result.get().getPassword());
//        assertEquals(user.getPhone(), result.get().getPhone());
//        assertEquals(user.getRole(), result.get().getRole());
//
//    }

}