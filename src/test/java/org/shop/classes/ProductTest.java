package org.shop.classes;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.shop.classes.stubs.MicrowaveAdapterStub;
import org.shop.interfaces.DbcAdapter;


class ProductTest {

    @Test
    void conversionTest(){
        DbcAdapter<JSONObject> stub = new MicrowaveAdapterStub();
        Product.setDbcAdapter(stub);
        Product p = (Product) Product.convertFromRecord(1);
        System.out.println(p.convertToRecord());
        assert p.convertToRecord().equals(stub.adaptDataToDBFormat(stub.loadData(1, Object.class)));
    }

    @Test
    void setPriceTest(){
        DbcAdapter<JSONObject> stub = new MicrowaveAdapterStub();
        Product.setDbcAdapter(stub);
        Product p = (Product) Product.convertFromRecord(-9);
        p.setPrice(-100F);
        Newsletter n = Newsletter.getInstance();
        System.out.println(n.getProducts());
        assert true;
    }

}

