package org.shop.classes;

import org.json.JSONArray;
import org.json.JSONObject;
import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;
import org.shop.interfaces.Observer;

import java.util.*;

public class Product implements Convertible {

    volatile static private int freeId = -1;
    private int id;
    private String category;
    private String name;
    private String mark;
    private float price;
    private float oldPrice;
    private List<Observer> observers;
    private static int howManyStock;
    private Map<String, String> characteristics;

    public Product(String name, String mark, float price, int howManyStock){
        this.name = name;
        this.mark = mark;
        this.price = price;
        this.oldPrice = price;
        this.howManyStock = howManyStock;
        this.characteristics = new TreeMap<>();
        this.observers = new ArrayList<>();

        if(freeId < 0) {
            DatabaseConnector dbc = DatabaseConnector.getInstance();
            freeId = dbc.findFreeId(Product.class);
        }

        this.id = freeId++;
    }

    // For items loaded from database
    public Product(int id, String name, String mark, float price, float oldPrice, int howManyStock){
        this.id = id;
        this.name = name;
        this.mark = mark;
        this.price = price;
        this.oldPrice = oldPrice;
        this.howManyStock = howManyStock;
        this.characteristics = new TreeMap<>();
        this.observers = new ArrayList<>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
        // TODO: Database with observers
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
        // TODO: Remove from database
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(name, price);
        }
    }

    public void updateProductPrice(float newPrice) {
        if (price > newPrice) {
            setPrice(newPrice);
        }
    }

    @Override
    public String convertToRecord() {
        JSONObject record = new JSONObject();
        record.put("id", this.id);
        String[] category = this.category.split("/");
        record.put("category", category[category.length-1]);
        record.put("name", this.name);
        record.put("mark", this.mark);
        record.put("price", this.price);
        record.put("oldPrice", this.oldPrice);
        record.put("howManyStock", howManyStock);

        ArrayList<String> characteristicsValues = new ArrayList<>(this.characteristics.values());
        record.put("characteristics", characteristicsValues);
        DbcAdapter<JSONObject> dbcAdapter = new DbcAdapterRecordJSON();

        return dbcAdapter.adaptDataToDBFormat(record);
    }


    public static Convertible convertFromRecord(int id){
        DbcAdapter<JSONObject> dbcAdapter = new DbcAdapterRecordJSON();
        JSONObject json = dbcAdapter.loadData(id, Product.class);

        JSONArray jsonArray = json.getJSONArray("characteristics");
        ArrayList<String> characteristicsFromJSON = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++)
            characteristicsFromJSON.add((String) jsonArray.get(i));

        String category = json.getString("category");

        Product p = null;
        switch(category){
            case "cpu":
                p = ProductFactory.createCPU(json.getInt("id"), json.getString("name"), json.getString("mark"),
                        json.getFloat("price"),
                        json.getFloat("oldPrice"),
                        json.getInt("howManyStock"),
                        characteristicsFromJSON);
                break;
            case "fridge":
                p = ProductFactory.createFridge(json.getInt("id"), json.getString("name"), json.getString("mark"),
                        json.getFloat("price"),
                        json.getFloat("oldPrice"),
                        json.getInt("howManyStock"),
                        characteristicsFromJSON);
                break;
            case "laptop":
                p = ProductFactory.createLaptop(json.getInt("id"), json.getString("name"), json.getString("mark"),
                        json.getFloat("price"),
                        json.getFloat("oldPrice"),
                        json.getInt("howManyStock"),
                        characteristicsFromJSON);
                break;
            case "microwave":
                p = ProductFactory.createMicrowave(json.getInt("id"), json.getString("name"), json.getString("mark"),
                        json.getFloat("price"),
                        json.getFloat("oldPrice"),
                        json.getInt("howManyStock"),
                        characteristicsFromJSON);
                break;
        }

        return p;
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

    public void updateInBase(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.updateRecord(this);
    }

    public void updateObject(){
        DbcAdapter<JSONObject> dbcAdapter = new DbcAdapterRecordJSON();
        JSONObject json = dbcAdapter.loadData(id, Product.class);
        howManyStock = json.getInt("howManyStock");
    }

    private void notifyNewsletter(){
        Newsletter.getInstance().update(name, price - oldPrice);
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.oldPrice = this.price;
        this.price = price;
        updateInBase();
        notifyObservers();
        //notifyNewsletter();
    }

    public int getHowManyStock() {
        return howManyStock;
    }

    public void setHowManyStock(int howManyStock) {
        this.howManyStock = howManyStock;
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
        this.category = category;
    }

    public Map<String, String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Map<String, String> characteristics) {
        this.characteristics = characteristics;
    }

    public void addCharacteristic(String key, String value){
        this.characteristics.put(key, value);
    }

}
