package org.shop.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.classes.stubs.AddressAdapterStub;
import org.shop.interfaces.DbcAdapter;

class AddressTest {

    int id1 = 1;
    DbcAdapter<String> stub;

    @BeforeEach
    void init(){
        stub = new AddressAdapterStub();
    }

    @Test
    void conversionTest() {
        Address.setDbcAdapter(stub);
        Address a = (Address) Address.convertFromRecord(id1);
        assert a != null;
        assert a.convertToRecord().equals(stub.loadData(id1, Object.class));

    }

}