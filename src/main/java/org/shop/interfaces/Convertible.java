package org.shop.interfaces;

/**
 * Save and load entities to database*/
public interface Convertible {

    // Saves object to database
    String convertToRecord();

    // Convert string (record from database) into object
    static Convertible convertFromRecord(String record) {
        throw new UnsupportedOperationException();
    }

    // TODO
    // Convert record from database into object
    static Convertible convertFromRecord(int id) {
        throw new UnsupportedOperationException();
    }
}

