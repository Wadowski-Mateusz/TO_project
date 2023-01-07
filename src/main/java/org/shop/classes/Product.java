package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;

import static java.lang.Float.NaN;

public abstract class Product implements Convertible {

    volatile static private int freeId = -1;
    private int id;
    private String name;
    private String producent;
    private String category;
    private float basePrice;
    private float discountPrice;
    private ArrayList<Tag> tags;
    private ArrayList<Product> suggested;
    private int howManyStock;
    private Boolean visibility;

    public int getId() {
        return id;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Product> getSuggested() {
        return suggested;
    }

    public void setSuggested(ArrayList<Product> suggested) {
        this.suggested = suggested;
    }

    public int getHowManyStock() {
        return howManyStock;
    }

    public void setHowManyStock(int howManyStock) {
        this.howManyStock = howManyStock;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    @Override
    public final String convertToRecord() {
        throw new UnsupportedOperationException("Cannot save product to base. It should be added manually.");
    }

}
