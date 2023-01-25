package org.shop.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.classes.stubs.MicrowaveAdapterStub;
import org.shop.classes.stubs.UserAdapterStub;
import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

import java.util.ArrayList;

class NewsletterTest {

    DbcAdapter productStub;
    DbcAdapter userStub;

    @BeforeEach
    void init(){
        productStub = new MicrowaveAdapterStub();
        Product.setDbcAdapter(productStub);

        userStub = new UserAdapterStub();
        User.setDbcAdapter(userStub);
    }


    @Test
    void getInstance() {
        Newsletter news = Newsletter.getInstance();
        Newsletter news2 = Newsletter.getInstance();
        if(news2.hashCode() == news.hashCode())
            assert true;
        else
            assert false;
    }

    @Test
    void update() {
        Newsletter.setDIR("src/test/mails/");

        User u1 = (User) User.convertFromRecord(1);
        User u2 = (User) User.convertFromRecord(2);
        User u3 = (User) User.convertFromRecord(3);
        assert u1 != null;  u1.subscribeNewsletter();
        assert u2 != null;  u2.subscribeNewsletter();
        assert u3 != null;  u3.subscribeNewsletter();

        Product p1 = (Product) Product.convertFromRecord(1);
        Product p2 = (Product) Product.convertFromRecord(2);
        Product p3 = (Product) Product.convertFromRecord(3);
        Product p4 = (Product) Product.convertFromRecord(4);

        // tets
        p1.setPrice(0.0F);
        p2.setPrice(1.0F);
        p3.setPrice(2.0F);
        p4.setPrice(3.0F);
        
        assert true;
    }

    @Test
    void addUser() {
        Newsletter news = Newsletter.getInstance();
        User u1 = (User) User.convertFromRecord(1);
        User u2 = (User) User.convertFromRecord(2);
        User u3 = (User) User.convertFromRecord(3);

        assert u1 != null;  u1.subscribeNewsletter();
        assert u2 != null;  u2.subscribeNewsletter();
        assert u3 != null;  u3.subscribeNewsletter();

        ArrayList<Integer> ids = (ArrayList<Integer>) news.getUsersId();

        if(ids.contains(u1.getId()) && ids.contains(u2.getId()) && ids.contains(u3.getId()))
            assert true;
        else
            assert false;

    }

    @Test
    void deleteUser() {
        Newsletter news = Newsletter.getInstance();
        User u1 = (User) User.convertFromRecord(1);
        User u2 = (User) User.convertFromRecord(2);
        User u3 = (User) User.convertFromRecord(3);

        assert u1 != null;  u1.subscribeNewsletter();
        assert u2 != null;  u2.subscribeNewsletter();
        assert u3 != null;  u3.subscribeNewsletter();

        ArrayList<Integer> ids = (ArrayList<Integer>) news.getUsersId();

        u2.unsubscribeNewsletter();

        if(ids.contains(u2.getId()))
            assert false;
        else
            assert true;
    }


}