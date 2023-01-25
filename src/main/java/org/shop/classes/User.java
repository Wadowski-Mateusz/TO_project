package org.shop.classes;

import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

import java.util.ArrayList;

public class User implements Convertible {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isAdmin;
    private Address address;
    private Cart cart;
    private ArrayList<Order> orderHistory;

    private static DbcAdapter<String> dbcAdapter = new DbcAdapterRecordString();


    public static UserBuilder getBuilder(){
        return new UserBuilder();
    }

    public User(){}

    public User(int id, String name, String surname, String email, String password,
                String phoneNumber, Address address, Cart cart,
                Boolean isAdmin, ArrayList<Order> orderHistory){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cart = cart;
        this.isAdmin = isAdmin;
        this.orderHistory = orderHistory;
    }

    public User(String email, String password) {
        this.id = 0;
        this.email = email;
        this.password = password;
    }

    public User(String[] data){
        int i = 0;
        this.id = Integer.parseInt(data[i++]);
        this.name = data[i++];
        this.surname = data[i++];
        this.email = data[i++];
        this.password = data[i++];
        this.phoneNumber = data[i++];
        this.address = (Address) Address.convertFromRecord(this.id);
        this.cart = (Cart) Cart.convertFromRecord(this.id);
        this.isAdmin = Boolean.parseBoolean(data[i++]);
        this.orderHistory = new ArrayList<>();
        for(int j = i; j < data.length; j++)
            orderHistory.add((Order) Order.convertFromRecord(Integer.parseInt(data[j])));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateInBase();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        updateInBase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        updateInBase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        updateInBase();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        updateInBase();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        updateInBase();
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        updateInBase();
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setRole(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        updateInBase();
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
        updateInBase();
    }

    public static void setDbcAdapter(DbcAdapter db) {
        dbcAdapter = db;
    }

    @Override
    public String convertToRecord() {
        String record = this.id + ","
                + this.name + "," + this.surname + ","
                + this.email + "," + this.password + ","
                + this.phoneNumber + "," + this.isAdmin;

        for (Order o : orderHistory)
            record += ", " + o.getId();

        return dbcAdapter.adaptDataToDBFormat(record);
    }

    static public Convertible convertFromRecord(int id) {
        String record = dbcAdapter.loadData(id, User.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new User(data);
    }

    public void updateInBase(){
        dbcAdapter.updateInBase(this);
    }

    public void updateObject(){
        String record = dbcAdapter.loadData(id, User.class);
        String[] data = record.split(",");

        int i = 1;
        this.name = data[i++];
        this.surname = data[i++];
        this.email = data[i++];
        this.password = data[i++];
        this.phoneNumber = data[i++];
        this.address = (Address) Address.convertFromRecord(this.id);
        this.cart = (Cart) Cart.convertFromRecord(this.id);
        this.isAdmin = Boolean.parseBoolean(data[i++]);
        this.orderHistory = new ArrayList<>();
        for(int j = i; j < data.length; j++)
            orderHistory.add((Order) Order.convertFromRecord(Integer.parseInt(data[j])));

    }

    void subscribeNewsletter(){
        Newsletter.getInstance().addUser(this);
    }

    void unsubscribeNewsletter(){
        Newsletter.getInstance().deleteUser(this);
    }


}
