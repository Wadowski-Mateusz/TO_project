package org.shop.classes.Products;

import org.shop.classes.Product;
import org.shop.interfaces.Convertible;

public class Laptop extends Product {
    public String category = "computer/computer/laptop";
    public int[] resolution;
    public float inches;
    public int batterySize;

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
