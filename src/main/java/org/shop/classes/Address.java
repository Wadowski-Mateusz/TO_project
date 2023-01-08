package org.shop.classes;

import org.shop.interfaces.Convertible;

public class Address implements Convertible {

    private int id;
    private String street;
    private String house;
    private String zip;
    private String city;
    private String voivodeships;

    public Address(int id) {
        this.id = id;
        this.street = "";
        this.house = "";
        this.zip = "";
        this.city = "";
        this.voivodeships = "";

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(!dbc.saveToFile(this)){
            System.out.println("Saving to file failed");
        }
    }

    public Address(int id, String street, String house, String zip, String city, String voivodeships) {
        this.id = id;
        this.street = street;
        this.house = house;
        this.zip = zip;
        this.city = city;
        this.voivodeships = voivodeships;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(!dbc.saveToFile(this)){
            System.out.println("Saving to file failed");
        }
    }

    private Address(String[] data){
        if(data.length != 6)
            throw new IllegalArgumentException("Bad number of parameters\n");
        this.id = Integer.parseInt(data[0]);
        this.street = data[1];
        this.house = data[2];
        this.zip = data[3];
        this.city = data[4];
        this.voivodeships = data[5];
    }

    public int getId() {return id;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public String getHouse() {return house;}

    public void setHouse(String house) {this.house = house;}

    public String getZip() {return zip;}

    public void setZip(String zip) {this.zip = zip;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getVoivodeships() {return voivodeships;}

    public void setVoivodeships(String voivodeships) {this.voivodeships = voivodeships;}

    @Override
    public String convertToRecord() {
        return id + "," + street + "," + house + "," + zip + "," + city + "," + voivodeships;
    }

    static public Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadFromFile(id, Address.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");

        return new Address(data);
    }

}
