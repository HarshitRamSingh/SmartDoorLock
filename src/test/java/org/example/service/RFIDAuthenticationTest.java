package org.example.service;

import org.example.model.Attempt;
import org.example.model.RFID;
import org.example.model.TryCounter;
import org.example.model.UsersDB;
import org.example.model.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RFIDAuthenticationTest {
    private RFIDAuthentication rfidAuthentication;
    private UsersDB usersDB;
    private Attempt attempt;
    private TryCounter tryCounter;
    private Logger logger;

    @BeforeEach
    void setUp() {
        // Initialize your RFIDAuthentication here
        usersDB = mock(UsersDB.class);
        attempt = mock(Attempt.class);
        tryCounter = mock(TryCounter.class);
        logger = mock(Logger.class);
        rfidAuthentication = new RFIDAuthentication(usersDB, attempt, tryCounter, logger);
    }

    @Test
    void testAuthenticateRFIDWithValidRFID() {
        String validUserID = "1234567890"; // replace with a valid UserID
        RFID validRFID = new RFID("validRFID"); // replace with a valid RFID
        when(rfidAuthentication.authenticateRFID(validUserID, validRFID, usersDB)).thenReturn(true);
        assertTrue(rfidAuthentication.authenticateRFID(validUserID, validRFID, usersDB));
    }

    @Test
    void testAuthenticateRFIDWithInvalidRFID() {
        String validUserID = "1234567890"; // replace with a valid UserID
        RFID invalidRFID = new RFID("invalidRFID"); // replace with an invalid RFID
        when(rfidAuthentication.authenticateRFID(validUserID, invalidRFID, usersDB)).thenReturn(false);
        assertFalse(rfidAuthentication.authenticateRFID(validUserID, invalidRFID, usersDB));
    }

    @Test
    void testAuthenticateRFIDWithNullRFID() {
        String validUserID = "1234567890"; // replace with a valid UserID
        RFID nullRFID = null;
        when(rfidAuthentication.authenticateRFID(validUserID, nullRFID, usersDB)).thenReturn(false);
        assertFalse(rfidAuthentication.authenticateRFID(validUserID, nullRFID, usersDB));
    }
}