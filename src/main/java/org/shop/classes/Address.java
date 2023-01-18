package org.shop.classes;

import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

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
        if(!dbc.saveToFile(this))
            System.out.println("Saving to file failed");

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


    public Address(String street, String house, String zip, String city, String voivodeships) {
        this.street = street;
        this.house = house;
        this.zip = zip;
        this.city = city;
        this.voivodeships = voivodeships;
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

    public void setId(int id) { this.id = id;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street; updateInBase();}

    public String getHouse() {return house;}

    public void setHouse(String house) {this.house = house; updateInBase();}

    public String getZip() {return zip;}

    public void setZip(String zip) {this.zip = zip; updateInBase();}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city; updateInBase();}

    public String getVoivodeships() {return voivodeships;}

    public void setVoivodeships(String voivodeships) {this.voivodeships = voivodeships; updateInBase();}


    @Override
    public String convertToRecord() {
        String record =  id + "," + street + "," + house + "," + zip + "," + city + "," + voivodeships;
        DbcAdapterRecordString dbcAdapterRecordString = new DbcAdapterRecordString();
        return dbcAdapterRecordString.adaptDataToDBFormat(record);
    }

    static public Convertible convertFromRecord(int id) {
        DbcAdapter<String> dbcAdapter = new DbcAdapterRecordString();
        String record = (String) dbcAdapter.loadData(id, Address.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new Address(data);
    }

    public void updateInBase(){
        DatabaseConnector.getInstance().updateRecord(this);
    }

    public void updateObject(){
        DbcAdapter<String> dbcAdapter = new DbcAdapterRecordString();
        String record = (String) dbcAdapter.loadData(id, Address.class);
        String[] data = record.split(",");
        this.street = data[1];
        this.house = data[2];
        this.zip = data[3];
        this.city = data[4];
        this.voivodeships = data[5];
    }

}

