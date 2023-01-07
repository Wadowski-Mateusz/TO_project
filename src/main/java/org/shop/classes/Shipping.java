package org.shop.classes;

import org.shop.interfaces.Convertible;

/**
 * id same as order
 * */

public class Shipping implements Convertible {

    public static final String STATUS_SHIPPED = "wyslano";
    public static final String STATUS_RECIVED = "odebrano";
    public static final String STATUS_PREPARATION = "w przygotowaniu";
    private int id;
    private Address address;

    private String status;

    public Shipping(int id, Address address){
        this.id = id;
        this.status = Shipping.STATUS_PREPARATION;
        this.address = address;
    }

    private Shipping(String[] data){
        //TODO address from base
        this.id = Integer.parseInt(data[0]);
//        this.address = data[1];
        this.status = data[2];
    }

    public int getId() {
        return id;
    }

    public int getAddressId() {
        return address.getId();
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

    static Convertible convertFromRecord(String record) {
        String[] data = record.split(",");
        return new Shipping(data);
    }


}
