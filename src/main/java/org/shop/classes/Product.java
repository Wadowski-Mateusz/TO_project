package org.shop.classes;


import org.shop.interfaces.Convertible;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

public class Product implements Convertible {




    volatile static private int freeId = -1;
    private int id;
    private static String category;
    private String name;
    private String mark;
    private float price;
    private int howManyStock;
    private Boolean visibility;
    private ArrayList<Tag> tags;
    private ArrayList<Product> suggested;
    private TreeMap<String, String> characteristics;

    public Product(String name, String mark, float price, int howManyStock, Boolean visibility){
        this.name = name;
        this.mark = mark;
        this.price = price;
        this.howManyStock = howManyStock;
        this.visibility = visibility;
        this.tags = new ArrayList<>();
        this.suggested = new ArrayList<>();
        this.characteristics = new TreeMap<>();

        if(freeId < 0) {
            DatabaseConnector dbc = DatabaseConnector.getInstance();
            freeId = dbc.findFreeId(Product.class);
        }

        this.id = freeId++;
    }

    @Override
    public String convertToRecord() {
        throw new UnsupportedOperationException("product.convertToRecord unsupported");
    }

    public static Convertible convertFromRecord(int id){
        throw new UnsupportedOperationException("product.convertFromRecord unsupported");
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        Product.category = category;
    }

    public TreeMap<String, String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(TreeMap<String, String> characteristics) {
        this.characteristics = characteristics;
    }

    public void addCharacteristic(String key, String value){
        this.characteristics.put(key, value);
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
