package org.shop.classes;

import org.shop.interfaces.Convertible;

/**
 * id same as order
 * address same as user address
 * */

public class Shipping implements Convertible {

    public static final String STATUS_SHIPPED = "wyslano";
    public static final String STATUS_RECEIVED = "odebrano";
    public static final String STATUS_PREPARATION = "w przygotowaniu";
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String convertToRecord(){
        return this.id + "," + this.address.getId() + "," + this.status;
    }

    static Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadData(id, Shipping.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new Shipping(data);
    }

}
