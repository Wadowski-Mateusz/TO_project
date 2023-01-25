package org.shop.classes;

import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

public class Payment implements Convertible {

    public static final String STATUS_PAYMENT_FALSE = "Nieoplacone";
    public static final String STATUS_PAYMENT_TRUE = "Oplacone";
    private int id;
    private float value;
    private String status;
    private static DbcAdapter<String> dbcAdapter = new DbcAdapterRecordString();


    public Payment(){}

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
        updateInBase();
        Order order = (Order) Order.convertFromRecord(this.id);
        order.setStatus(status);
        order.updateInBase();
    }

    public float getValue() {
        return value;
    }

    void pay(){
        throw new UnsupportedOperationException();
    }


    public static void setDbcAdapter(DbcAdapter dbc) {
        dbcAdapter = dbc;
    }

    @Override
    public String convertToRecord() {
        String record = this.id + ","
                + String.format("%.2f", this.value).replace(",",".")
                + "," + this.status;
        return dbcAdapter.adaptDataToDBFormat(record);
    }

    static Convertible convertFromRecord(int id) {
        String record = dbcAdapter.loadData(id, Payment.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");

        return new Payment(data);
    }

    public void updateInBase(){
        dbcAdapter.updateInBase(this);
    }

    public void updateObject(){
        String record = dbcAdapter.loadData(id, Payment.class);
        String[] data = record.split(",");
        this.value = Float.parseFloat(data[1]);
        this.status = data[2];

    }

}
