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
        this.address = new Address(this.id); // TODO look in files
        this.cart = new Cart(this.id); // TODO look in files
        this.settings = new UserSettings(this.id); // TODO look in files
        this.role = User.STANDARD;
        this.orderHistory = new ArrayList<>();

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(this.freeId < 0)
            this.freeId = dbc.findFreeId(User.class);

        this.id = freeId++;

        if(!dbc.saveToFile(this)){
            System.out.println("Saving to file failed");
            this.id = -1;
            this.freeId -= 1;
        }

    }

    public User(String[] data){
        // TODO find address, cart and settings in base
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.surname = data[2];
        this.email = data[3];
        this.password = data[4];
        this.phoneNumber = data[5];
//        this.address = data[6];
//        this.cart = data[7];
//        this.settings = data[8];
        this.role = data[9];
        this.orderHistory = new ArrayList<>();
//        for(int i = 10; i < data.length; i++)
//            orderHistory.add(data[i]);
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
        String result = "";
        result = this.id + ","
                + this.name + "," + this.surname + ","
                + this.email + "," + this.password + ","
                + this.phoneNumber + "," + this.address.getId() + ","
                + this.cart.getId() + "," + this.settings.getId() + ","
                + this.role;
        if (this.orderHistory.size() > 0) {
            result += "," + orderHistory.toString();
            result = result.replace(" ", "");
            result = result.replace("[", "");
            result = result.replace("]", "");
        }
        return result;
    }

    static public Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String[] data = db.recordFromFile(id, User.class).split(",");
        if(data.equals(null))
            return null;
        return new User(data);
    }

}
