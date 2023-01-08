package org.shop.classes.Products;

import org.shop.classes.Product;
import org.shop.classes.Tag;
import org.shop.interfaces.Convertible;

import java.util.ArrayList;

public class Laptop extends Product {
    public static final String CATEGORY = "computer/computer/laptop/";
    public int[] resolution;
    public float inches;
    public int batterySize;

    public Laptop(String name, String mark, float basePrice, float discountPrice, ArrayList<Tag> tags, ArrayList<Product> suggested, int howManyStock, Boolean visibility) {
        super(name, mark, basePrice, discountPrice, tags, suggested, howManyStock, visibility);
    }

    // TODO
    static Convertible convertFromRecord(String record) {
        throw new UnsupportedOperationException();
    }
    // TODO
    static Convertible convertFromRecord(int id) {
        throw new UnsupportedOperationException();
    }
    @Override
    public String convertToRecord() {
        throw new UnsupportedOperationException();
    }
}
