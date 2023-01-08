package org.shop.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectorTest {

    DatabaseConnector bd;
    @BeforeEach
    void init(){
        bd = DatabaseConnector.getInstance();
    }
    @Test
    void findFileRecursive() {
    }

}