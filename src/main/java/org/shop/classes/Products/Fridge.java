package org.shop.classes.Products;

import org.shop.classes.Product;
import org.shop.classes.Tag;
import org.shop.interfaces.Convertible;

import java.util.ArrayList;

public class Fridge extends Product {

    public static final String CATEGORY = "agd/kitchen/fridge/";
    public float volume;
    public int power;
    public String energyEfficiencyClass;
    public float[] size;

    public Fridge(String name, String mark, float basePrice, ArrayList<Tag> tags, ArrayList<Product> suggested, int howManyStock, Boolean visibility) {
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
