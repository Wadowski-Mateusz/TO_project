package org.shop.classes;

import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

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
        updateInBase();
        Order order = (Order) Order.convertFromRecord(this.id);
        order.setStatus(status);
        order.updateInBase();
    }

    public float getValue() {
        return value;
    }

    // TODO - musi updatować zamówienie
    void pay(){
        throw new UnsupportedOperationException();
    }

    @Override
    public String convertToRecord() {
        String record = this.id + ","
                + String.format("%.2f", this.value).replace(",",".")
                + "," + this.status;
        DbcAdapterRecordString dbcAdapterRecordString = new DbcAdapterRecordString();
        return dbcAdapterRecordString.adaptDataToDBFormat(record);
    }

    static Convertible convertFromRecord(int id) {
        DbcAdapter dbcAdapter = new DbcAdapterRecordString();
        String record = (String) dbcAdapter.loadData(id, Payment.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return (data != null) ? new Payment(data) : null;
    }

    public void updateInBase(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.updateRecord(this);
    }

    public void updateObject(){
        DbcAdapter dbcAdapter = new DbcAdapterRecordString();
        String record = (String) dbcAdapter.loadData(id, Payment.class);
        String[] data = record.split(",");
        this.value = Float.parseFloat(data[1]);
        this.status = data[2];

    }

}
