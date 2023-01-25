package org.shop.classes.stubs;

import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

public class AddressAdapterStub implements DbcAdapter<String> {

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