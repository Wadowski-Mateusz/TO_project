package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Responsible for adding and returning data from base.
 * Singleton.
 */
public final class DatabaseConnector {

    private static final String DIR = "data/";
    private static final String DIR_PRODUCTS = "data/products/";
    private static volatile DatabaseConnector instance;

    private DatabaseConnector() {
    }

    public static DatabaseConnector getInstance() {

        DatabaseConnector result = instance;
        if (result != null)
            return result;

        synchronized (DatabaseConnector.class) {
            if (instance == null)
                instance = new DatabaseConnector();
            return instance;
        }

    }

    /**
     * example: ListFiles("data/") returns list filled with names of files in "project_path/data/" directory
     *
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
     *
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
    private int maxIdFile(String path) {
        try {
            File file = new File(path);
            String line = "";
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine())
                line = myReader.nextLine();
            myReader.close();
            String[] data = line.split(",");

            return (data[0].equals("id")) ? -1 : Integer.parseInt(data[0]);

        } catch (FileNotFoundException e) {
            System.out.println("No such a file:\t" + path);
            return -2;
        }
    }

    /**
     * @return success: free id for Product; failure: -1
     */
    private int findFreeProductId() {
        ArrayList<String> files = listAllFilesFromDirectory(DatabaseConnector.DIR_PRODUCTS);
        int maxId = -2;
        for (String file : files) {
            int foundId = maxIdFile(file);
            if (foundId > maxId)
                maxId = foundId;
        }
        return maxId + 1;
    }

    /**
     * example: findFreeID(Address.class)
     * example: findFreeID(Product.class) - for every class extending Product
     *
     * @param convertible object which id have to be found
     * @return first free id for given class
     */
    public int findFreeId(Class convertible) {
        if (Product.class.equals(convertible))
            return findFreeProductId();

        return maxIdFile(DIR + convertible.getSimpleName() + ".csv") + 1;
    }

    /**
     * @param convertible object convertible with file in "date/*"
     * @return path to file for argument
     */
    private String findFilePath(Convertible convertible) {
        String fileName;
        if (convertible instanceof Product) {
            fileName = ((Product) convertible).getCategory() + ".csv";
            System.out.println(fileName);
            return DIR_PRODUCTS + fileName;
        } else {
            fileName = convertible.getClass().getSimpleName().toLowerCase() + ".csv";
            return DIR + fileName;
        }
    }

    /**
     * @param convertible object to save to database
     * @return on success returns true; on failure returns false
     */
    public boolean saveToFile(Convertible convertible) {
        String path = findFilePath(convertible);
//        System.out.println("Path:" + path + "\tData: " + convertible.convertToRecord());
        try {
            Writer output = new BufferedWriter(new FileWriter(path, true));
            output.append(System.lineSeparator()).append(convertible.convertToRecord());
            output.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * @param lookIn directory to recursively find all .csv files
     * @return success: all paths to .csv files in subdirectories; failure: empty list
     */
    private ArrayList<String> listAllFilesFromDirectory(String lookIn) {
        ArrayList<String> result = new ArrayList<>();

        ArrayList<String> directories = (ArrayList<String>) listDirectories(lookIn);
        if (directories.isEmpty()) {
            result = (ArrayList<String>) listFiles(lookIn);
            for (int i = 0; i < result.size(); i++)
                result.set(i, lookIn + result.get(i));
            return result;
        }

        for (String directory : directories)
            result.addAll(listAllFilesFromDirectory(lookIn + directory + "/"));

        return result;
    }

    /**
     * Looks for record with given id in file.
     *
     * @param id   id of record to find
     * @param path path to file with record
     * @return on success: record in csv form; on failure: empty string
     */
    private String recordFromFile(int id, String path) {
        Scanner scanner;

        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            System.out.println("recordFromFile(): No such a file");
            return "";
        }

        scanner.nextLine(); //header
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int foundId = Integer.parseInt(line.split(",")[0]);
            if (foundId == id) {
                scanner.close();
                return line;
            }
        }
        scanner.close();
        return "";
    }

    /**
     * Load record of given id and given type.
     * example: loadData(0, User.class)
     *
     * @param id          if of object method has to find
     * @param convertible instance of item we are looking for
     * @return string containing record from base; empty on failure
     */
    public String loadData(int id, Class convertible) {
        if (!convertible.equals(Product.class))
            return recordFromFile(id, DIR + convertible.getSimpleName().toLowerCase() + ".csv");

        ArrayList<String> files = listAllFilesFromDirectory(DIR_PRODUCTS);
        for (String file : files) {
            String record = recordFromFile(id, file);
            if (!record.isEmpty())
                return record;
        }

        return ""; // TODO throw error
    }

    /**
     * Updates existing record in the database of given instance.
     *
     * @param convertible object to update
     * @return on success returns true; on failure returns false
     */
    public boolean updateRecord(Convertible convertible) {
        try {
            String path = findFilePath(convertible);
            BufferedReader file = new BufferedReader(new FileReader(path));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            int id = Integer.parseInt(convertible.convertToRecord().split(",")[0]);
            while ((line = file.readLine()) != null) {
//                System.out.println(line);
                if (Integer.parseInt(line.split(",")[0]) == id) {
                    line = convertible.convertToRecord();
                    inputBuffer.append(line).append('\n');
                    break;
                }
                inputBuffer.append(line).append('\n');
            }

            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(path);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
            return true;
        } catch (IOException e) {
            System.out.println("updateRecord() error");
            return false;
        }
    }

    /**
     * example: listProductsFromCategory("microwave")
     * Returns map of products from given category, which are visible and in stock
     *
     * @param category category of product ex. "microwave"
     * @return on success: map filled with ids and names; on failure: null
     */
    public TreeMap<Integer, String> listProductsFromCategory(String category) {
        String path = "";
        for (String file : listAllFilesFromDirectory(DIR_PRODUCTS))
            if (file.contains(category + ".csv")) {
                path = file;
                break;
            }
        TreeMap<Integer, String> output = new TreeMap<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            scanner.nextLine(); //header
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                if (Boolean.parseBoolean(line[6]) && Integer.parseInt(line[5]) > 0)
                    output.put(Integer.valueOf(line[0]), line[2]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("listProductsFromCategory(): No such a file as " + path);
            return null;
        }
        return output;
    }

    /**
     * Only admin should be able to use it
     * example: listProductsFromCategory("microwave")
     * Returns list of all products from given category
     *
     * @param category category of product ex. "microwave"
     * @return on success: list filled with ids and names; on failure: null
     */
    public TreeMap<Integer, String> listAllProductsFromCategory(String category) {
        String path = "";
        for (String file : listAllFilesFromDirectory(DIR_PRODUCTS))
            if (file.contains(category + ".csv")) {
                path = file;
                break;
            }
        TreeMap<Integer, String> output = new TreeMap<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            scanner.nextLine(); //header
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                output.put(Integer.valueOf(line[0]), line[2]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("listAllProductsFromCategory(): No such a file");
            return null;
        }
        return output;
    }

    /**
     * Looks for user with given data in the database and checks if the user is an admin
     * example: isAdmin("aa.aa@aa.aa","password")
     *
     * @param email    user mail
     * @param password user password
     * @return on success: true; on failure: false
     */
    public boolean isUserAdmin(String email, String password) {
        boolean isAdmin = false;
        try (Scanner scanner = new Scanner(new File(DIR + "user.csv"))) {
            scanner.nextLine(); // header
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                if (line[6].equals("true")) {
                    isAdmin = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("verificationUserLoginData(): No such a file");
            System.exit(-1);
        }
        return isAdmin;
    }

    /**
     * Looks for user with given data in the database and checks if there is a user with the given email
     * example: isEmail("aa.aa@aa.aa","password")
     *
     * @param email    user mail
     * @return on success: true; on failure: false
     */
    public boolean isEmail(String email) {
        boolean existsInDatabase = false;
        try (Scanner scanner = new Scanner(new File(DIR + "user.csv"))) {
            scanner.nextLine(); // header
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                if (line[3].equals(email)) {
                    existsInDatabase = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("verificationUserLoginData(): No such a file");
            System.exit(-1);
        }
        return existsInDatabase;
    }

    /**
     * Looks for user with given data in base
     * example: verificationUserLoginData("aa.aa@aa.aa","password")
     *
     * @param email    user mail
     * @param password user password
     * @return on success: user id; on failure: -1
     */
    public int verificationUserLoginData(String email, String password) {
        int id = -1;
        try (Scanner scanner = new Scanner(new File(DIR + "user.csv"))) {
            scanner.nextLine(); //header
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                if (line[3].equals(email) && line[4].equals(password)) {
                    id = Integer.parseInt(line[0]);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("verificationUserLoginData(): No such a file");
            System.exit(-1);
        }
        return id;
    }

}
