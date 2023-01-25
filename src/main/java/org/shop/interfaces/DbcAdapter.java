package org.shop.interfaces;

import org.shop.classes.DatabaseConnector;

public interface DbcAdapter<T> {

    DatabaseConnector db = null;

    public T loadData(int id, Class convertible);

    public String adaptDataToDBFormat(T t);

    public void updateInBase(Convertible convertible);

}
