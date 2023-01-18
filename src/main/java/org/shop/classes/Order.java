package org.shop.classes;

import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

import java.util.ArrayList;


/**
 * shipping and payment id same as Order id
 * Maybe change productsId to ArrayList<Product> and use ID only for save and init
 * */
public class Order implements Convertible {

    volatile static private int freeId = -1;
    private int id;
    private float value;
    private String status;
    private ArrayList<Product> products;
    private Shipping shipping;
    private Payment payment;

    public Order(float value, ArrayList<Product> products){
        this.value = value;
        this.products = products;
        this.status = Payment.STATUS_PAYMENT_FALSE;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(freeId < 0)
            freeId = dbc.findFreeId(User.class);
        this.id = freeId++;

        this.payment = new Payment(this.id, this.value);
        this.shipping = null;
    }

    private Order(String[] data){
        int i = 0;
        this.id = Integer.parseInt(data[i++]);
        this.value = Float.parseFloat(data[i++]);
        this.status = data[i++];
        this.shipping = (Shipping) Shipping.convertFromRecord(this.id);
        this.payment = (Payment) Payment.convertFromRecord(this.id);
        this.products = new ArrayList<>();
        while(i < data.length){
            products.add((Product) Product.convertFromRecord(Integer.parseInt(data[i])));
            i++;
        }

    }

    public int getId() {
        return id;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
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
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.updateRecord(this);
    }

    @Override
    public String convertToRecord() {
        String record = this.id + ",";
        record += String.format("%.2f", this.value).replace(",",".") + ",";
        record += status;
        for(Product p : products)
            record += "," + p.getId();
        DbcAdapterRecordString dbcAdapterRecordString = new DbcAdapterRecordString();
        return dbcAdapterRecordString.adaptDataToDBFormat(record);
    }

    static Convertible convertFromRecord(int id) {
        DbcAdapter dbcAdapter = new DbcAdapterRecordString();
        String record = (String) dbcAdapter.loadData(id, Order.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new Order(data);
    }

    public void updateInBase(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.updateRecord(this);
    }

    public void updateObject(){
        DbcAdapter dbcAdapter = new DbcAdapterRecordString();
        String record = (String) dbcAdapter.loadData(id, Order.class);
        String[] data = record.split(",");

        this.value = Float.parseFloat(data[1]);
        this.status = data[2];
        this.shipping = (Shipping) Shipping.convertFromRecord(id);
        this.payment = (Payment) Payment.convertFromRecord(id);
        this.products.clear();
        for(int i = 5; i < data.length; i++)
            products.add((Product) Product.convertFromRecord(Integer.parseInt(data[i])));

    }

}
