package org.shop.classes;


/**
 * Responsible for adding and returning data from base.
 * Singleton.
 * */
public final class DatabaseConnector {

    private static volatile DatabaseConnector instance;
    private volatile Converter converter;

    private DatabaseConnector() {
        converter = new Converter();
    }

    public static DatabaseConnector getInstance() {
        DatabaseConnector result = instance;

        if (result != null)
            return result;

        synchronized(DatabaseConnector.class) {
            if (instance == null) {
                instance = new DatabaseConnector();
            }
            return instance;
        }
    }

}
