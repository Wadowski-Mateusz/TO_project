package org.shop.classes.stubs;

import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

public class UserAdapterStub implements DbcAdapter<String> {

    @Override
    public String loadData(int id, Class convertible) {
        return id + ","
                + "stubName" + id + ","
                + "stubSurname" + id + ","
                + "stub@email.stub" + id + ","
                + "stubPassword" + id + ","
                + "stubPhoneNumber" + id + ","
                + false; // isAdmin
                // no order history
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
