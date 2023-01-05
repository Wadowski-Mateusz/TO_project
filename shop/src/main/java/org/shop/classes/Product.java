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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getLowestPriceMonth() {
        return LowestPriceMonth;
    }

    public void setLowestPriceMonth(float lowestPriceMonth) {
        LowestPriceMonth = lowestPriceMonth;
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

}
