package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.io.*;
import java.util.List;
import java.util.Objects;
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
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
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
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(File::isDirectory)
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
     * example: findFreeID(Address.class)
     * example: findFreeID(Product.class) - for every class extending Product
     * @param convertible object which id have to be found
     * @return first free id for given class
     */
    public int findFreeId(Class convertible){
        if (Product.class.equals(convertible))
            return findIdRecursive(DIR_PRODUCTS) + 1;

        String[] className = convertible.getName().split("\\.");
        String path = DIR + className[className.length-1].toLowerCase() + ".csv";
        return maxIdFile(path) + 1;
    }

    /**
     * @param lookFor look for file, example "user.csv"
     * @param lookIn look in this directory and subdirectories
     * @return on success, path to file; on failure empty string
     */
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

    /**
     * @param convertible object convertible to csv with file in "date/"
     * @return path to file for argument
     */
    private String findFile(Convertible convertible){
        String[] classData = convertible.getClass().getName().split("\\.");
        String fileName = classData[classData.length-1].toLowerCase() + ".csv";

        return (convertible instanceof Product)
                ? findFileRecursive(fileName, DIR_PRODUCTS)
                : DIR + fileName;
    }

    /**
     * @param convertible object to save to database
     * @return on success returns true; on failure returns false
     */
    public boolean saveToFile(Convertible convertible){
        String path = findFile(convertible);
        System.out.println("Path:" + path + "\tData: " + convertible.convertToRecord());
        try {
            Writer output = new BufferedWriter(new FileWriter(path, true));
            output.append(System.lineSeparator()).append(convertible.convertToRecord());
            output.close();
        } catch (IOException e){
            return false;
        }
        return true;
    }


    /**
     * @param id if of object method has to find
     * @param convertible instance of item we are looking for
     * @return string containing record from base; empty on failure
     */
    public String recordFromFile(int id, Class convertible)  {
        try {
            Object ob = convertible.newInstance();
            String path = findFile((Convertible) ob);
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            scanner.nextLine(); //header
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int foundId = Integer.parseInt(line.split(",")[0]);
                if(foundId == id) {
                    scanner.close();
                    return line;
                }
            }
            scanner.close();
            return "";
        } catch (FileNotFoundException e) {
            System.out.println("recordFromFile(): No such a file");
            return "";
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("recordFromFile() error");
            return "";
        }
    }

}
