package org.shop.classes.Products;

import org.shop.classes.Product;
import org.shop.interfaces.Convertible;

public class Fridge extends Product {

    public String category = "agd/kitchen/fridge";
    public float volume;
    public int power;
    public String energyEfficiencyClass;
    public float[] size;

    // TODO
    static Convertible convertFromRecord(String record) {
        throw new UnsupportedOperationException();
    }
    // TODO
    static Convertible convertFromRecord(int id) {
        throw new UnsupportedOperationException();
    }
}
