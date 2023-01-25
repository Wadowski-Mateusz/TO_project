package org.shop.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

class AddressTest {

    int id1 = 1;
    DbcAdapter<String> stub;

    @BeforeEach
    void init(){
        stub = new AdapterStub();
    }

    @Test
    void convertTest() {

        Address.setDbcAdapter(stub);
        Address a = (Address) Address.convertFromRecord(id1);
        assert a != null;
        assert a.convertToRecord().equals(stub.loadData(id1, Object.class));

    }

    public class AdapterStub implements DbcAdapter<String> {

        @Override
        public String loadData(int id, Class convertible) {
            return id + ","
                    + "stubStreet" + id + ","
                    + "stubHouse" + id + ","
                    + "stubZip" + id + ","
                    + "stubCity" + id + ","
                    + "stubVoivodeships" + id;
        }

        @Override
        public String adaptDataToDBFormat(String s) {
            return s;
        }

        @Override
        public void updateInBase(Convertible convertible) {
            throw new UnsupportedOperationException();
        }
    }

}