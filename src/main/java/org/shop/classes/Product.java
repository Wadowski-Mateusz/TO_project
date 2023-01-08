package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;
import java.util.Objects;


// TODO convertible methods

public abstract class Product implements Convertible {

    public static final String CATEGORY = "";
    static private int freeId = -1;
    private int id;
    private String name;
    private String mark;
    private float price;
    private ArrayList<Tag> tags;
    private ArrayList<Product> suggested;
    private int howManyStock;
    private Boolean visibility;

    public Product(String name, String mark, float price, ArrayList<Tag> tags, ArrayList<Product> suggested, int howManyStock, Boolean visibility) {
        this.name = name;
        this.mark = mark;
        this.price = price;
        this.tags = tags;
        this.suggested = suggested;
        this.howManyStock = howManyStock;
        this.visibility = visibility;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(freeId < 0)
            freeId = dbc.findFreeId(Product.class);

        this.id = freeId++;

        if(!dbc.saveToFile(this)){
            System.out.println("Failed save to file");
            this.id = -1;
            freeId -= 1;
        }
    }



    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public static Convertible convertFromRecord(int id){
        throw new UnsupportedOperationException("product.convertFromRecord unsupported");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
