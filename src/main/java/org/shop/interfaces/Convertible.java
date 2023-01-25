package org.shop.interfaces;

/**
 * Save and load entities to database*/
public interface Convertible {

    static DbcAdapter dbcAdapter = null;
    public static void setDbcAdapter(DbcAdapter dbcAdapter){
        throw new UnsupportedOperationException();
    }

    // Saves object to database
    String convertToRecord();

    // Convert string (record from database) into object
    static Convertible convertFromRecord(int id) {
        throw new UnsupportedOperationException();
    }

    void updateInBase(); // old void update();
    void updateObject();

}

