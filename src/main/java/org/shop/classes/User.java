package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;

public class User implements Convertible {

    private final int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isAdmin;
    private Address address;
    private Cart cart;
    private UserSettings settings;
    private ArrayList<Order> orderHistory;

    public static UserBuilder getBuilder(){
        return new UserBuilder();
    }

    public User(int id, String name, String surname, String email, String password,
                String phoneNumber, Address address, Cart cart,
                UserSettings userSettings, Boolean isAdmin, ArrayList<Order> orderHistory){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cart = cart;
        this.settings = userSettings;
        this.isAdmin = isAdmin;
        this.orderHistory = orderHistory;
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
        this.settings = (UserSettings) UserSettings.convertFromRecord(this.id);
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
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public UserSettings getSettings() {
        return settings;
    }

    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setRole(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public String convertToRecord() {
        String record = this.id + ","
                + this.name + "," + this.surname + ","
                + this.email + "," + this.password + ","
                + this.phoneNumber + "," + this.isAdmin;

        for (Order o : orderHistory)
            record += ", " + o.getId();

        return record;
    }

    static public Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadData(id, User.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new User(data);
    }

}
