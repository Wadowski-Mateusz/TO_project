package org.shop.classes;

import java.util.ArrayList;

public class ProductFactory {

    private static Product createProduct(String name, String mark, float price,
                                         int howManyStock, Boolean visibility){

        Product product = new Product(name, mark, price, howManyStock, visibility);
        return product;
    }

    public static Product createMicrowave(String name, String mark, float price,
                                          int howManyStock, Boolean visibility, ArrayList<String> characteristics){
        // Create product
        Product microwave = ProductFactory.createProduct(name, mark, price, howManyStock, visibility);
        microwave.setCategory(ProductCategory.MICROWAVE);

        // Generate suggested items
        // ...


        int i = 0;
        microwave.addCharacteristic("power", characteristics.get(i++));
        microwave.addCharacteristic("volume", characteristics.get(i++));

        // TODO save to database

        return microwave;
    }

    public static Product createFridge(String name, String mark, float price,
                                       int howManyStock, Boolean visibility, ArrayList<String> characteristics){
        // Create product
        Product fridge = ProductFactory.createProduct(name, mark, price, howManyStock, visibility);
        fridge.setCategory(ProductCategory.FRIDGE);

        // Generate suggested items
        // ...


        int i = 0;
        fridge.addCharacteristic("volume", characteristics.get(i++));
        fridge.addCharacteristic("power", characteristics.get(i++));
        fridge.addCharacteristic("energyEfficiencyClass", characteristics.get(i++));
        fridge.addCharacteristic("width", characteristics.get(i++));
        fridge.addCharacteristic("length", characteristics.get(i++));
        fridge.addCharacteristic("height", characteristics.get(i++));

        // TODO save to database

        return fridge;
    }
    public static Product createLaptop(String name, String mark, float price,
                                       int howManyStock, Boolean visibility, ArrayList<String> characteristics){
        // Create product
        Product laptop = ProductFactory.createProduct(name, mark, price, howManyStock, visibility);
        laptop.setCategory(ProductCategory.LAPTOP);

        // Generate suggested items
        // ...


        int i = 0;
        laptop.addCharacteristic("resolution", characteristics.get(i++)); // np 1920x1080
        laptop.addCharacteristic("inches", characteristics.get(i++));
        laptop.addCharacteristic("battery", characteristics.get(i++));

        // TODO save to database

        return laptop;
    }
    public static Product createCPU(String name, String mark, float price,
                                    int howManyStock, Boolean visibility, ArrayList<String> characteristics){
        // Create product
        Product cpu = ProductFactory.createProduct(name, mark, price, howManyStock, visibility);
        cpu.setCategory(ProductCategory.CPU);

        // Generate suggested items
        // ...


        int i = 0;
        cpu.addCharacteristic("cores", characteristics.get(i++));
        cpu.addCharacteristic("clock", characteristics.get(i++));

        // TODO save to database

        return cpu;
    }


}
