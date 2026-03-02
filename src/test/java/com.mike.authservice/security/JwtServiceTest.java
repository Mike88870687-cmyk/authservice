package com.mike.authservice.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private static final String EMAIL = "test@test.com";
    private static final long TEST_EXPIRATION = 6000;

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService(TEST_EXPIRATION);
    }

    @Test
    void shouldGenerateValidToken() {
        String token = jwtService.generateToken(EMAIL);

        assertNotNull(token);
        assertTrue(jwtService.isTokenValid(token));
    }

    @Test
    void shouldExtractCorrectUsername() {
        String token = jwtService.generateToken(EMAIL);

        assertEquals(EMAIL, jwtService.extractUsername(token));
    }

    @Test
    void shouldRejectTamperedToken() {
        String token = jwtService.generateToken(EMAIL);
        String tamperedToken = token + "abc";

        assertFalse(jwtService.isTokenValid(tamperedToken));
    }

    @Test
    void shouldRejectExpiredToken() throws InterruptedException {
        String token = jwtService.generateToken(EMAIL);

        Thread.sleep(TEST_EXPIRATION + 100);

        assertFalse(jwtService.isTokenValid(token));
    }
}
