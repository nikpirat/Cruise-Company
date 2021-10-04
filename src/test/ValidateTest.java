package test;


import org.junit.Test;
import service.Validate;

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

public class ValidateTest {

    @Test
    public void testCheckUser() {
        Validate validate = mock(Validate.class);

        String login = "login";
        String password = "password";

        when(validate.checkUser(login, password)).thenReturn(true);
        assertEquals(validate.checkUser(login, password), true);
    }

    @Test
    public void testCheckLogin() {
        Validate validate = mock(Validate.class);

        String login = "login";

        when(validate.checkLogin(login)).thenReturn(true);
        assertEquals(validate.checkLogin(login), true);
    }

    @Test
    public void testCheckLoginWithNullLogin() {
        Validate validate = mock(Validate.class);

        String login = null;

        when(validate.checkLogin(login)).thenReturn(false);
        assertEquals(validate.checkLogin(login), false);
    }
    @Test
    public void testCheckLoginWithNumbers() {
        Validate validate = mock(Validate.class);

        String login = "123431";

        when(validate.checkLogin(login)).thenReturn(true);
        assertEquals(validate.checkLogin(login), true);
    }

}