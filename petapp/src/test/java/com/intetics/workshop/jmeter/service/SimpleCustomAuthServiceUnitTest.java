package com.intetics.workshop.jmeter.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SimpleCustomAuthServiceUnitTest {

    private SimpleCustomAuthService authService = new SimpleCustomAuthService();
    private static final int TOKEN_EXPIRATION_IN_SECONDS = 3;

    @Before
    public void setUp() throws Exception {
        authService.setTokensExpiration(TOKEN_EXPIRATION_IN_SECONDS);
    }

    @Test
    public void testAuthenticateReturnsDifferentTokens() throws Exception {
        String token1 = authService.authenticate("mylogin1", "secretpassword1").getToken();
        String token2 = authService.authenticate("mylogin2", "secretpassword2").getToken();
        assertNotEquals(token1, token2);
    }

    @Test
    public void testUnknownTokenIsNotValid() throws Exception {
        assertFalse(authService.isValid("unknowntoken12345"));
    }

    @Test
    public void testTokenValidAfterAuthentication() throws Exception {
        String token = authService.authenticate("mylogin", "secretpassword").getToken();
        assertTrue(authService.isValid(token));
    }

    @Test
    public void testTokenExpiration() throws Exception {
        String token = authService.authenticate("mylogin", "secretpassword").getToken();
        assertTrue(authService.isValid(token));

        Thread.sleep((TOKEN_EXPIRATION_IN_SECONDS + 1) * 1000);
        assertFalse(authService.isValid(token));
    }

    @Test
    public void testTokenValidationExtendsTokenExpiration() throws Exception {
        String token1 = authService.authenticate("mylogin1", "secretpassword1").getToken();
        String token2 = authService.authenticate("mylogin2", "secretpassword2").getToken();

        Thread.sleep((TOKEN_EXPIRATION_IN_SECONDS - 1) * 1000);
        assertTrue(authService.isValid(token1));

        Thread.sleep((TOKEN_EXPIRATION_IN_SECONDS - 1) * 1000);
        assertTrue(authService.isValid(token1));
        assertFalse(authService.isValid(token2));
    }

    @Test
    public void testAuthenticationWithSameCredentialsInvalidatesPreviousTokens() throws Exception {
        String token1 = authService.authenticate("mylogin", "secretpassword").getToken();
        assertTrue(authService.isValid(token1));

        String token2 = authService.authenticate("mylogin", "secretpassword").getToken();
        assertTrue(authService.isValid(token2));
        assertFalse(authService.isValid(token1));
    }
}