package org.shop.classes;


import java.util.ArrayList;

public class CartBuilder {
    private int id;
    private ArrayList<Product> products;
    private float value;

    public CartBuilder(){

    }

    public CartBuilder setId(int id){
        this.id = id;
        return this;
    }

    public CartBuilder addProduct(Product product){
        this.products.add(product);
        this.value += product.getPrice();
        return this;
    }

    public CartBuilder removeProduct(Product product){
        for (int i = 0; i < this.products.size(); i++)
            if(products.get(i) == product) {
                this.products.remove(i);
                    break;
            }
        this.value -= product.getPrice();
        return this;
    }

    public Cart build(){
        return new Cart(this.id, this.value, this.products);
    }

}
