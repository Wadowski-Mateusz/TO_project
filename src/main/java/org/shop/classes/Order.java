package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;


/**
 * shipping and payment id same as Order id
 * Maybe change productsId to ArrayList<Product> and use ID only for save and init
 * */
public class Order implements Convertible {

    public static final String STATUS_FINALIZED = "Zrealizowano";
    public static final String STATUS_PAYMENT_FALSE = "Nieoplacone";
    public static final String STATUS_PAYMENT_TURE = "Oplacone";
    public static final String STATUS_SHIPPED = "Wyslane";

    volatile static private int freeId = -1;
    private int id;
    private float value;
    private String status;
    private ArrayList<Integer> productsId;

    public Order(int value, ArrayList<Integer> productsId){
        this.value = value;
        this.productsId = productsId;
        this.status = STATUS_PAYMENT_FALSE;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(freeId < 0)
            freeId = dbc.findFreeId(User.class);

        this.id = freeId++;

        if(!dbc.saveToFile(this)){
            System.out.println("Saving to file failed");
            this.id = -1;
            freeId -= 1;
        }

    }

    private Order(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.value = Float.parseFloat(data[1]);
        this.status = data[2];
        this.productsId = new ArrayList<>();
        for(int i = 3; i < data.length; i++)
            productsId.add(Integer.parseInt(data[i]));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getProducts() {
        return productsId;
    }

    public void setProducts(ArrayList<Integer> products) {
        this.productsId = products;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String convertToRecord() {
        String record = this.id + ",";
        record += String.format("%.2f", this.value).replace(",",".") + ",";
        record += status;
        record += "," + productsId.toString();
        record = record.replace(" ", "");
        record = record.replace("[", "");
        record = record.replace("]", "");
        return record;
    }
    static Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadFromFile(id, Order.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new Order(data);
    }
}
