package org.shop.classes;

import java.util.ArrayList;

public class ProductFactory {

    private static Product createProduct(String name, String mark, float price,
                                         int howManyStock){

        return new Product(name, mark, price, howManyStock);
    }

    public static Product createMicrowave(String name, String mark, float price,
                                          int howManyStock, ArrayList<String> characteristics){
        // Create product
        Product microwave = ProductFactory.createProduct(name, mark, price, howManyStock);
        microwave.setCategory(ProductCategory.MICROWAVE);


        int i = 0;
        microwave.addCharacteristic("power", characteristics.get(i++));
        microwave.addCharacteristic("volume", characteristics.get(i));

        return microwave;
    }

    public static Product createFridge(String name, String mark, float price,
                                       int howManyStock, ArrayList<String> characteristics){
        // Create product
        Product fridge = ProductFactory.createProduct(name, mark, price, howManyStock);
        fridge.setCategory(ProductCategory.FRIDGE);

        int i = 0;
        fridge.addCharacteristic("volume", characteristics.get(i++));
        fridge.addCharacteristic("width", characteristics.get(i++));
        fridge.addCharacteristic("length", characteristics.get(i++));
        fridge.addCharacteristic("height", characteristics.get(i));


        return fridge;
    }

    public static Product createLaptop(String name, String mark, float price,
                                       int howManyStock, ArrayList<String> characteristics){
        // Create product
        Product laptop = ProductFactory.createProduct(name, mark, price, howManyStock);
        laptop.setCategory(ProductCategory.LAPTOP);

        int i = 0;
        laptop.addCharacteristic("inches", characteristics.get(i++));
        laptop.addCharacteristic("battery", characteristics.get(i));

        return laptop;
    }

    public static Product createCPU(String name, String mark, float price,
                                    int howManyStock, ArrayList<String> characteristics){
        // Create product
        Product cpu = ProductFactory.createProduct(name, mark, price, howManyStock);
        cpu.setCategory(ProductCategory.CPU);

        int i = 0;
        cpu.addCharacteristic("cores", characteristics.get(i++));
        cpu.addCharacteristic("clock", characteristics.get(i));

        return cpu;
    }



    /**
     * Versions with id (loading from database)
     * */

    private static Product createProduct(int id, String name, String mark, float price,
                                         int howManyStock){

        return new Product(id, name, mark, price, howManyStock);
    }

    public static Product createMicrowave(int id, String name, String mark, float price,
                                          int howManyStock, ArrayList<String> characteristics){
        // Create product
        Product microwave = ProductFactory.createProduct(id, name, mark, price, howManyStock);
        microwave.setCategory(ProductCategory.MICROWAVE);


        int i = 0;
        microwave.addCharacteristic("power", characteristics.get(i++));
        microwave.addCharacteristic("volume", characteristics.get(i));

        return microwave;
    }

    public static Product createFridge(int id, String name, String mark, float price,
                                       int howManyStock, ArrayList<String> characteristics){
        // Create product
        Product fridge = ProductFactory.createProduct(id, name, mark, price, howManyStock);
        fridge.setCategory(ProductCategory.FRIDGE);

        int i = 0;
        fridge.addCharacteristic("volume", characteristics.get(i++));
        fridge.addCharacteristic("width", characteristics.get(i++));
        fridge.addCharacteristic("length", characteristics.get(i++));
        fridge.addCharacteristic("height", characteristics.get(i));


        return fridge;
    }

    public static Product createLaptop(int id, String name, String mark, float price,
                                       int howManyStock, ArrayList<String> characteristics){
        // Create product
        Product laptop = ProductFactory.createProduct(id, name, mark, price, howManyStock);
        laptop.setCategory(ProductCategory.LAPTOP);

        int i = 0;
        laptop.addCharacteristic("inches", characteristics.get(i++));
        laptop.addCharacteristic("battery", characteristics.get(i));

        return laptop;
    }

    public static Product createCPU(int id, String name, String mark, float price,
                                    int howManyStock, ArrayList<String> characteristics){
        // Create product
        Product cpu = ProductFactory.createProduct(id, name, mark, price, howManyStock);
        cpu.setCategory(ProductCategory.CPU);

        int i = 0;
        cpu.addCharacteristic("cores", characteristics.get(i++));
        cpu.addCharacteristic("clock", characteristics.get(i));

        return cpu;
    }





}
