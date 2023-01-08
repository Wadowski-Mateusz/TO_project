package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;

import static java.lang.Float.NaN;

// TODO convertible methods

public abstract class Product implements Convertible {

    public static final String CATEGORY = "";
    static private int freeId = -1;
    private int id;
    private String name;
    private String mark;
    private float basePrice;
    private float discountPrice;
    private ArrayList<Tag> tags;
    private ArrayList<Product> suggested;
    private int howManyStock;
    private Boolean visibility;

    public Product(String name, String mark, float basePrice, float discountPrice, ArrayList<Tag> tags, ArrayList<Product> suggested, int howManyStock, Boolean visibility) {
        this.name = name;
        this.mark = mark;
        this.basePrice = basePrice;
        this.discountPrice = discountPrice;
        this.tags = tags;
        this.suggested = suggested;
        this.howManyStock = howManyStock;
        this.visibility = visibility;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(this.freeId < 0)
            this.freeId = dbc.findFreeId(Product.class);

        this.id = freeId++;

        if(!dbc.saveToFile(this)){
            System.out.println("Failed save to file");
            this.id = -1;
            this.freeId -= 1;
        }
    }



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

    /**
     * For adding items to database. Only for admin. */
    @Override
    public String convertToRecord() {
        throw new UnsupportedOperationException("product.convertToRecord unsupported");
    }

    public Convertible convertFromRecord(int id){
        throw new UnsupportedOperationException("product.convertFromRecord unsupported");
//        DatabaseConnector db = DatabaseConnector.getInstance();
//        String[] data = db.recordFromFile(id, .class).split(",");
//        if(data.equals(null))
//            return null;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
