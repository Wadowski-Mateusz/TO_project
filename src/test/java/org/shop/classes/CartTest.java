package org.shop.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.interfaces.Convertible;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Cart cart;
    @BeforeEach
    void init(){
        cart = new Cart(1);
    }

    @Test
    void convertToRecord() {
        String correct = "1,0.00";
        assertEquals(correct, cart.convertToRecord());
    }

    @Test
    void convertToRecord_multi() {
        String correct = "1,0.00";
        ArrayList<Integer> a = new ArrayList();
        a.add(1);
        a.add(1);
        a.add(1);
        correct += ",1,1,1";
        cart.setProductsId(a);
        assertEquals(correct, cart.convertToRecord());
    }

    @Test
    void convertFromRecord(){
        Convertible cart2 = Cart.convertFromRecord("1,0");
        assertEquals(cart2, cart);

        ArrayList<Integer> a = new ArrayList<>();
        a.add(0);
        a.add(1);
        a.add(2);
        a.add(3);
        cart.setProductsId(a);
        cart.setValue(123.45F);
        Convertible cart3 = Cart.convertFromRecord("1,123.45,0,1,2,3");
        assertEquals(cart3, cart);


        a.clear();
        a.add(4);
        cart.setProductsId(a);
        cart.setValue(0.1F);
        Convertible cart4 = Cart.convertFromRecord("1,0.1,4");
        assertEquals(cart4, cart);
    }
}