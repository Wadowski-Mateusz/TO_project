package org.shop.classes;

import org.shop.interfaces.Convertible;

public class Payment implements Convertible {

    public static final String STATUS_PAYMENT_FALSE = "Nieoplacone";
    public static final String STATUS_PAYMENT_TRUE = "Oplacone";
    private final int id;
    private float value;
    private String status;

    /**
     * @param id same as order id
     * @param value same as order value
     */
    public Payment(int id, float value){
        this.id = id;
        this.value = value;
        this.status = STATUS_PAYMENT_FALSE;
//        DatabaseConnector dbc = DatabaseConnector.getInstance();
//        if(!dbc.saveToFile(this)){
//            System.out.println("Failed save to file");
//        }
    }

    private Payment(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.value = Float.parseFloat(data[1]);
        this.status = data[2];
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        update();
        Order order = (Order) Order.convertFromRecord(this.id);
        order.setStatus(status);
        order.update();
    }

    public float getValue() {
        return value;
    }

    @Override
    public String convertToRecord() {
        String result = this.id + ","
                + String.format("%.2f", this.value).replace(",",".")
                + "," + this.status;
        return result;
    }

    static Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadData(id, Payment.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new Payment(data);
    }

    public void update(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.updateRecord(this);
    }

    public void updateObject(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadData(id, Order.class);
//        if(record.isEmpty())
//            throw new //todo
        String[] data = record.split(",");
        this.value = Float.parseFloat(data[1]);
        this.status = data[2];

    }

}
