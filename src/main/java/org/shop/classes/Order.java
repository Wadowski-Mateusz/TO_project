package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;


/**
 * shipping and payment.csv id same as Order id
 * Maybe change productsId to ArrayList<Protuct> and use ID only for save and init
 * */
public class Order implements Convertible {

    public static final String status_finalized = "Zrealizowano";
    public static final String status_payment_false = "Nieoplacone";
    public static final String status_payment_true = "Oplacone";
    public static final String status_shipped = "Wyslane";

    volatile static private int freeId = -1;
    private int id;
    private float value;
    private String status;
    private ArrayList<Integer> productsId;

    public Order(int value, ArrayList<Integer> productsId){
        //TODO id from database
        this.value = value;
        this.productsId = productsId;
        this.status = status_payment_false;
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
        this.productsId = productsId;
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
        String str = "";
        str = String.valueOf(this.id) + ",";
        str += String.format("%.2f", this.value).replace(",",".") + ",";
        str += status;
        str += "," + productsId.toString();
        str = str.replace(" ", "");
        str = str.replace("[", "");
        str = str.replace("]", "");
        return str;
    }
    static Convertible convertFromRecord(String record) {
        String[] data = record.split(",");
        return new Order(data);
    }
}
