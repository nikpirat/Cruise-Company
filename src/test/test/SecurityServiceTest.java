package test;


import org.junit.Test;
import service.SecurityService;

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
        assertEquals(securityService.checkUser(login, password), true);
    }

    @Test
    public void testCheckLogin() {
        SecurityService securityService = mock(SecurityService.class);

        String login = "login";

        when(securityService.checkLogin(login)).thenReturn(true);
        assertEquals(securityService.checkLogin(login), true);
    }

    @Test
    public void testCheckLoginWithNullLogin() {
        SecurityService securityService = mock(SecurityService.class);

        String login = null;

        when(securityService.checkLogin(login)).thenReturn(false);
        assertEquals(securityService.checkLogin(login), false);
    }
    @Test
    public void testCheckLoginWithNumbers() {
        SecurityService securityService = mock(SecurityService.class);

        String login = "123431";

        when(securityService.checkLogin(login)).thenReturn(true);
        assertEquals(securityService.checkLogin(login), true);
    }

}