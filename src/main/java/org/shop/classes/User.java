package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;

public class User implements Convertible {
    public static final String STANDARD = "standard";
    public static final String ADMIN = "admin";

    volatile static private int freeId = -1;
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;
    private Cart cart;
    private UserSettings settings;
    private String role;
    private ArrayList<Order> orderHistory;



    public User(String password, String email){

        this.name = "";
        this.surname = "";
        this.email = email;
        this.password = password;
        this.phoneNumber = "";
        this.address = new Address(this.id);
        this.cart = new Cart(this.id);
        this.settings = new UserSettings(this.id);
        this.role = User.STANDARD;
        this.orderHistory = new ArrayList<>();

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(freeId < 0)
            freeId = dbc.findFreeId(User.class);

        this.id = freeId++;
//
//        if(!dbc.saveToFile(this)){
//            System.out.println("Saving to file failed");
//            this.id = -1;
//            freeId -= 1;
//        }

    }

    public User(String[] data){
        int i = 0;
        this.id = Integer.parseInt(data[i++]);
        this.name = data[i++];
        this.surname = data[i++];
        this.email = data[i++];
        this.password = data[i++];
        this.phoneNumber = data[i++];
        this.address = (Address) Address.convertFromRecord(Integer.parseInt(data[i++]));
        this.cart = (Cart) Cart.convertFromRecord(Integer.parseInt(data[i++]));
        this.settings = (UserSettings) UserSettings.convertFromRecord(Integer.parseInt(data[i++]));
        this.role = data[i++];
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
                + this.phoneNumber + "," + this.address.getId() + ","
                + this.cart.getId() + "," + this.settings.getId() + ","
                + this.role;

        for (Order o : orderHistory)
            record += ", " + o.getId();

        return record;
    }

    static public Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadFromFile(id, User.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new User(data);
    }

}
