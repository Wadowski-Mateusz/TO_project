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
    private ArrayList<Product> products;
    private Shipping shipping;
    private Payment payment;

    public Order(float value, ArrayList<Product> products){
        this.value = value;
        this.products = products;
        this.status = STATUS_PAYMENT_FALSE;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(freeId < 0)
            freeId = dbc.findFreeId(User.class);
        this.id = freeId++;

        this.payment = new Payment(this.id, this.value);
        this.shipping = null;
    }

    private Order(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.value = Float.parseFloat(data[1]);
        this.status = data[2];
        this.shipping = (Shipping) Shipping.convertFromRecord(Integer.parseInt(data[3]));
        this.payment = (Payment) Payment.convertFromRecord(Integer.parseInt(data[4]));
        this.products = new ArrayList<>();
        for(int i = 5; i < data.length; i++)
            products.add((Product) Product.convertFromRecord(Integer.parseInt(data[i])));
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
    }

    @Override
    public String convertToRecord() {
        String record = this.id + ",";
        record += String.format("%.2f", this.value).replace(",",".") + ",";
        record += status + ",";
        record += this.shipping.getId() + ",";
        record += this.payment.getId();
        for(Product p : products)
            record += "," + p.getId();
        return record;
    }

    static Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadData(id, Order.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new Order(data);
    }
}
