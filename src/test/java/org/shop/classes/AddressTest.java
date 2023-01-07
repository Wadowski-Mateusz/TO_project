package org.shop.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.interfaces.Convertible;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    Address address;

    @BeforeEach
    void init(){
        address = new Address(0, "street0","house0","zip0","city0","voivodeships0");
    }


    @Test
    void convertToRecordTest() {
        String correct = "0,street0,house0,zip0,city0,voivodeships0";
        assertEquals(address.convertToRecord(), correct);
    }

    @Test
    void convertFromRecordTest(){
        Convertible address2 = Address.convertFromRecord("0,street0,house0,zip0,city0,voivodeships0");
        assertEquals(address, address2);
        Convertible address3 = Address.convertFromRecord("0,streset0,hosuse0,zip0,city0,voivodeships0");
        assertNotEquals(address, address3);
    }

}