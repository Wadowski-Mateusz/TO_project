package org.shop.classes;


import org.shop.interfaces.Convertible;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Responsible for adding and returning data from base.
 * Singleton.
 * */
public final class DatabaseConnector {

    private static final String DIR = "data/";
    private static final String DIR_PRODUCTS = "data/products/";
    private static volatile DatabaseConnector instance;

    private DatabaseConnector() {
    }
    /**
     * Singleton thread-safe*/
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

    public int findFreeId(String fileName){
        try {
            File file = new File(DIR + fileName + ".csv");
            Scanner myReader = new Scanner(file);
            String line = "";
            while (myReader.hasNextLine())
                line = myReader.nextLine();
            myReader.close();
            String[] data = line.split(",");

            return (data[0].equals("id")) ?  0 : Integer.parseInt(data[0]);

        } catch(FileNotFoundException e){
            System.out.println("No such a file");
            return -1;
        }
    }


    public int findFreeId(Product product){
        File file = new File(DIR + product.getCategory() + ".csv");
        throw new UnsupportedOperationException();
    }

    public boolean saveToFile(Convertible convertible, String fileName){
        throw new UnsupportedOperationException();
    }

    public boolean saveToFile(Product product/*Path in class*/){
        throw new UnsupportedOperationException();
    }

    public Convertible readFromFile(int id, String fileName){
        throw new UnsupportedOperationException();
    }

}
