package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.Objects;

public class Address implements Convertible {

    volatile private int freeId = -1;   //jeśli == -1, klasa sprawdzi w pliku pierwsze wolne id; jeśli >=0, to uzyje tego id i zikrementuje
    private int id;
    private String street;
    private String house;
    private String zip;
    private String city;
    private String voivodeships;

    public Address(String street, String house, String zip, String city, String voivodeships) {

        this.street = street;
        this.house = house;
        this.zip = zip;
        this.city = city;
        this.voivodeships = voivodeships;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(this.freeId < 0)
            this.freeId = dbc.findFreeId(Address.class);

        this.id = freeId++;

       if(!dbc.saveToFile(this)){
           System.out.println("Failed save to file");
           this.id = -1;
           this.freeId -= 1;
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

    static public Convertible convertFromRecord(String record) {
        String[] data = record.split(",");
        return new Address(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && street.equals(address.street) && house.equals(address.house) && zip.equals(address.zip) && city.equals(address.city) && voivodeships.equals(address.voivodeships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, house, zip, city, voivodeships);
    }
}
