package org.shop.classes;

import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

/**
 * id same as order
 * address same as user address
 * */

public class Shipping implements Convertible {

    public static final String STATUS_SHIPPED = "wyslano";
    public static final String STATUS_RECEIVED = "odebrano";
    public static final String STATUS_PREPARATION = "w przygotowaniu";
    public static final String STATUS_RETURNED = "zwrocono";
    private final int id;
    private Address address;
    private String status;

    public Shipping(int id, Address address){
        this.id = id;
        this.address = address;
        this.status = Shipping.STATUS_PREPARATION;
//        DatabaseConnector dbc = DatabaseConnector.getInstance();
//        if(!dbc.saveToFile(this)){
//            System.out.println("Failed save to file");
//        }
    }

    private Shipping(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.address = (Address) Address.convertFromRecord(Integer.parseInt(data[1]));
        this.status = data[2];
    }

    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
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

    public String convertToRecord(){
        String record = this.id + "," + this.address.getId() + "," + this.status;
        DbcAdapterRecordString dbcAdapterRecordString = new DbcAdapterRecordString();
        return dbcAdapterRecordString.adaptDataToDBFormat(record);
    }

    static Convertible convertFromRecord(int id) {
        DbcAdapter<String> dbcAdapter = new DbcAdapterRecordString();
        String record = (String) dbcAdapter.loadData(id, Shipping.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new Shipping(data);
    }

    public void updateInBase(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.updateRecord(this);
    }

    public void updateObject(){
        DbcAdapter<String> dbcAdapter = new DbcAdapterRecordString();
        String record = (String) dbcAdapter.loadData(id, Shipping.class);
        String[] data = record.split(",");
        this.address = (Address) Address.convertFromRecord(Integer.parseInt(data[1]));
        this.status = data[2];
    }

}
