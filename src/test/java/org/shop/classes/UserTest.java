package org.shop.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.classes.stubs.UserAdapterStub;
import org.shop.interfaces.DbcAdapter;

class UserTest {

    DbcAdapter stub;

    @BeforeEach
    void init(){
        stub = new UserAdapterStub();
        User.setDbcAdapter(stub);
    }

    @Test
    void conversionTest(){
        User u = (User) User.convertFromRecord(1);
        assert u != null;
        assert u.convertToRecord().equals(stub.loadData(1, null));
    }

}