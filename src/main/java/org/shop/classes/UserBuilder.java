package org.shop.classes;

import java.util.ArrayList;

public class UserBuilder {

    volatile static private int freeId = -1;
    private int id = -1;
    private String name = "";
    private String surname = "";
    private String email = "";
    private String password = "";
    private String phoneNumber = "";
    private Boolean isAdmin  = false;
    private Address address = null;
    private Cart cart = null;
    private ArrayList<Order> orderHistory = null;


    public UserBuilder(){
    }

    public UserBuilder setName(String name){
        this.name = name;
        return this;
    }

    public UserBuilder setSurname(String surname){
        this.surname = surname;
        return this;
    }

    public UserBuilder setEmail(String email){
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    public UserBuilder setIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
        return this;
    }

    public UserBuilder setPhoneNUmber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder setAddress(Address address){
        this.address = address;
        return this;
    }

    public UserBuilder setOrderHistory(ArrayList<Order> orderHistory){
        this.orderHistory = orderHistory;
        return this;
    }

    public User build(){
        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(freeId < 0)
            freeId = dbc.findFreeId(User.class);
        this.id = freeId++;

        if(address == null) address = new Address(id);
        if(cart == null) cart = new Cart(id);
        if(orderHistory == null) orderHistory = new ArrayList<>();



        User u = new User(id, name, surname, email, password, phoneNumber,
                address, cart, isAdmin, orderHistory);

        if (dbc.saveToFile(u))
            return u;
        else
            return null; // TODO throw error
    }

}
