package org.shop.classes.Products;

import org.shop.classes.Product;
import org.shop.classes.Tag;
import org.shop.interfaces.Convertible;

import java.util.ArrayList;

public class CPU extends Product {
    public static final String CATEGORY = "computer/computer_parts/cpu/"; //path in database!

    public int cores;
    public int threads;
    public float gHz;

    public CPU(String name, String mark, float basePrice, ArrayList<Tag> tags, ArrayList<Product> suggested, int howManyStock, Boolean visibility) {
        super(name, mark, basePrice, tags, suggested, howManyStock, visibility);
    }


    // TODO
    public static Convertible convertFromRecord(int id) {
        throw new UnsupportedOperationException();
    }
    @Override
    public String convertToRecord() {
        throw new UnsupportedOperationException();
    }
}
