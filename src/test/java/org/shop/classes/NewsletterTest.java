package org.shop.classes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

class NewsletterTest {

    @Test
    void getInstance() {
        Newsletter news = Newsletter.getInstance();
        Newsletter news2 = Newsletter.getInstance();
        if(news2.hashCode() == news2.hashCode())
            assert true;
        else
            assert false;
    }

    @Test
    void update() {
        Newsletter news = Newsletter.getInstance();

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

        float[] prices = new float[4];
        prices[0] = p1.getPrice();
        prices[1] = p2.getPrice();
        prices[2] = p3.getPrice();
        prices[3] = p4.getPrice();

        float[] oldPrices = new float[4];
        oldPrices[0] = p1.getPrice();
        oldPrices[1] = p2.getPrice();
        oldPrices[2] = p3.getPrice();
        oldPrices[3] = p4.getPrice();


        // tets
        p1.setPrice(11.0F);
        p2.setPrice(12.0F);
        p3.setPrice(12.0F);
        p4.setPrice(13.0F);

        // back
        p1.setPrice(oldPrices[0]);
        p2.setPrice(oldPrices[1]);
        p3.setPrice(oldPrices[2]);
        p4.setPrice(oldPrices[3]);

        p1.setPrice(prices[0]);
        p2.setPrice(prices[1]);
        p3.setPrice(prices[2]);
        p4.setPrice(prices[3]);
        
        assert true;
    }

    @Test
    void addUser() {
        Newsletter news = Newsletter.getInstance();
        User u1 = (User) User.convertFromRecord(1);
        User u2 = (User) User.convertFromRecord(2);
        User u3 = (User) User.convertFromRecord(3);

        assert u1 != null;
        u1.subscribeNewsletter();
        assert u2 != null;
        u2.subscribeNewsletter();
        assert u3 != null;
        u3.subscribeNewsletter();

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

        assert u1 != null;
        u1.subscribeNewsletter();
        assert u2 != null;
        u2.subscribeNewsletter();
        assert u3 != null;
        u3.subscribeNewsletter();

        ArrayList<Integer> ids = (ArrayList<Integer>) news.getUsersId();

        u2.unsubscribeNewsletter();

        if(ids.contains(u2.getId()))
            assert false;
        else
            assert true;
    }





}