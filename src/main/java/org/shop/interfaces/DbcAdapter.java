package org.shop.interfaces;

public interface DbcAdapter<T> {
    public T loadData(int id, Class convertible);

    public String adaptDataToDBFormat(T t);

}
