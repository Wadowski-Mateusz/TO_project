package org.shop.classes;


import org.shop.classes.Products.Microwave;
import org.shop.interfaces.Convertible;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Responsible for adding and returning data from base.
 * Singleton.
 * */
public final class DatabaseConnector {

    private static final String DIR = "data/";
    private static final String DIR_PRODUCTS = "data/products/";
    private static volatile DatabaseConnector instance;

    private DatabaseConnector() {}

    public static DatabaseConnector getInstance() {

        DatabaseConnector result = instance;
        if (result != null)
            return result;

        synchronized(DatabaseConnector.class) {
            if (instance == null)
                instance = new DatabaseConnector();
            return instance;
        }

    }

    /**
     * example: ListFiles("data/") returns list filled with names of files in "project_path/data/" directory
     * @param dir path to directory
     * @return list of files in given directory
     */
    public List<String> listFiles(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    /**
     * example: ListDirectories("data/products/") returns list filled with names of directories in "project_path/data/product/" directory
     * @param dir path to directory
     * @return list of files in given directory
     */
    public List<String> listDirectories(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    /**
     * @param path path to file to retrieve id
     * @return max id from given file
     */
    private int maxIdFile(String path){
        try {
            File file = new File(path);
            String line = "";
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine())
                line = myReader.nextLine();
            myReader.close();
            String[] data = line.split(",");

            return (data[0].equals("id")) ?  -1 : Integer.parseInt(data[0]);

        } catch(FileNotFoundException e){
            System.out.println("No such a file:\t" + path);
            return -2;
        }
    }

    /**
     * @param path path to directory it will look for max id
     * @return max id from files in subdirectory
     */
    private int findIdRecursive(String path){
        List<String> directories = listDirectories(path);

        int maxId = -1;
        if (directories.isEmpty()) {
            List<String> files = listFiles(path);
            for(String file : files){
                int foundId = maxIdFile(path+file);
                if (foundId > maxId)
                    maxId = foundId;

            }
            return maxId;
        }

        for(String directory : directories){
            int foundId = findIdRecursive(path + directory + "/");
            if (foundId > maxId)
                maxId = foundId;
        }

        return maxId;
    }

    /**
     * @example: findFreeID(Address.class)
     * @example: findFreeID(Product.class) - for every class extending Product
     * @param convertible
     * @return first free id for given class
     */
    public int findFreeId(Class convertible){
        if (Product.class.equals(convertible))
            return findIdRecursive(DIR_PRODUCTS) + 1;

        String[] className = convertible.getName().split("\\.");
        String path = DIR + className[className.length-1].toLowerCase() + ".csv";
        return maxIdFile(path) + 1;
    }

    private String findFileRecursive(String lookFor, String lookIn){

        String foundPath = "";

        List<String> directories = listDirectories(lookIn);
        if(directories.isEmpty()){
            List<String> files = listFiles(lookIn);
            if (files.contains(lookFor))
                return lookIn + lookFor;
            else
                return "";
        }

        for(String directory : directories){
            foundPath = findFileRecursive(lookFor, lookIn + directory + "/");
            if(foundPath.contains(".csv"))
                break;
            else
                foundPath = "";
        }
        return foundPath;

    }

    private String findFile(Convertible convertible){
        String[] classData = convertible.getClass().getName().split("\\.");
        String fileName = classData[classData.length-1].toLowerCase() + ".csv";

        String path =
                (convertible instanceof Product)
                        ? findFileRecursive(fileName, DIR_PRODUCTS)
                        : DIR + fileName;
        return path;
    }

    public boolean saveToFile(Convertible convertible){

        String path = findFile(convertible);
//        System.out.println("Path: " + path);
        try {
            Writer output = new BufferedWriter(new FileWriter(path, true));
            output.append(System.lineSeparator() + convertible.convertToRecord());
            output.close();
        } catch (IOException e){
            return false;
        }

        return true;
    }


    public Convertible readFromFile(int id, String fileName){
        throw new UnsupportedOperationException();
    }

}
