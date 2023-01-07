package org.shop.classes.Products;

import org.shop.classes.Product;
import org.shop.interfaces.Convertible;

public class Microwave extends Product {

    public static final String CATEGORY = "agd/kitchen/microwave";
    public float volume;
    public int power;

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
