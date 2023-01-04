package org.shop.classes;

import java.util.ArrayList;

public class Product {
    private int id;
    private float basePrice;
    private float discountPrice;
    private float LowestPriceMonth;
    private ArrayList<Tag> tags;
    private ArrayList<Product> suggested;
    private int howManyStock;
    private Boolean visibility;
}
