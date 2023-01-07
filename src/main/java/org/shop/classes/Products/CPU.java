package org.shop.classes.Products;

import org.shop.classes.Product;
import org.shop.interfaces.Convertible;

public class CPU extends Product {
    public static final String CATEGORY = "computer/coputer_parts/cpu"; //path in database!

    public int cores;
    public int threads;
    public float gHz;

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
