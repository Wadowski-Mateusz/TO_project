package org.shop.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectorTest {

    DatabaseConnector dbc;

    @BeforeEach
    void init(){
        dbc = DatabaseConnector.getInstance();
    }

    @Test
    void findFreeId() {
        assertEquals(4, dbc.findFreeId("address"));
        assertNotEquals(7, dbc.findFreeId("address"));
        assertEquals(0, dbc.findFreeId("order"));
    }

    @Test
    void testFindFreeId() {
    }

    @Test
    void saveToFile() {
    }

    @Test
    void testSaveToFile() {
    }

    @Test
    void readFromFile() {
    }
}