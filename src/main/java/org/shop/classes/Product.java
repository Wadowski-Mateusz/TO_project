package org.shop.classes;


import org.json.JSONArray;
import org.json.JSONObject;
import org.shop.interfaces.Convertible;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

public class Product implements Convertible {

    volatile static private int freeId = -1;
    private int id;
    private String category;
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

        JSONObject record = new JSONObject();
        record.put("id",this.id);
        record.put("category", this.category);
        record.put("name", this.name);
        record.put("mark", this.mark);
        record.put("price", this.price);
        record.put("howManyStock", this.howManyStock);
        record.put("visibility", this.visibility.toString());

        ArrayList<Integer> tagId = new ArrayList<>();
        this.tags.forEach((Tag t) ->
                    tagId.add(t.getId()));
        record.put("tags", tagId);

//        ArrayList<String> suggestedId = new ArrayList<>();
//        this.suggested.forEach((Product p) ->
//                suggestedId.add(String.valueOf(p.getId()))
//        );
//        record.put("suggested", suggestedId);

        ArrayList<String> characteristicsValues = new ArrayList<>(this.characteristics.values());
        record.put("characteristics", characteristicsValues);

        return record.toString();
    }

    public static Convertible convertFromRecord(int id){
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadData(id, Product.class);
        JSONObject json = new JSONObject(record);

        JSONArray jsonArray = json.getJSONArray("characteristics");
        ArrayList<String> characteristicsFromJSON = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++)
            characteristicsFromJSON.add((String) jsonArray.get(i));

        String category = json.getString("category").split("/")[json.getString("category").split("/").length - 1];
        Product p = null;

        switch(category){
            case "cpu":
                p = ProductFactory.createCPU(json.getString("name"), json.getString("mark"),
                        json.getFloat("price"),
                        json.getInt("howManyStock"),
                        Boolean.parseBoolean(json.getString("visibility")),
                        characteristicsFromJSON);
                break;
            case "fridge":
                p = ProductFactory.createFridge(json.getString("name"), json.getString("mark"),
                        json.getFloat("price"),
                        json.getInt("howManyStock"),
                        Boolean.parseBoolean(json.getString("visibility")),
                        characteristicsFromJSON);
                break;
            case "laptop":
                p = ProductFactory.createLaptop(json.getString("name"), json.getString("mark"),
                        json.getFloat("price"),
                        json.getInt("howManyStock"),
                        Boolean.parseBoolean(json.getString("visibility")),
                        characteristicsFromJSON);
                break;
            case "microwave":
                p = ProductFactory.createMicrowave(json.getString("name"), json.getString("mark"),
                        json.getFloat("price"),
                        json.getInt("howManyStock"),
                        Boolean.parseBoolean(json.getString("visibility")),
                        characteristicsFromJSON);
                break;
        }

//        jsonArray = json.getJSONArray("suggested");
//        ArrayList<Pr> suggestedFromJSON = new ArrayList<>();
//        for(int i = 0; i < jsonArray.length(); i++)
//            suggestedFromJSON.add((String) jsonArray.get(i)); //na przedmiot
//        p.setSuggested(suggestedFromJSON);

        jsonArray = json.getJSONArray("tags");
        for(int i = 0; i < jsonArray.length(); i++) {
            Tag tag = (Tag) Tag.convertFromRecord(
                    (Integer) jsonArray.get(i));
            p.addTag(tag);
        }

        return p;
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
        this.category = category;
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

    public void addTag(Tag tag){
        this.tags.add(tag);
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
