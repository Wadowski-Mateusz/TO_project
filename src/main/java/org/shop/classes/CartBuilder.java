package org.shop.classes;


import java.util.ArrayList;

public class CartBuilder {
    private int id;
    private ArrayList<Product> products;
    private float value;

    public CartBuilder(){

    }

//    public CartBuilder(int id){
//        this.cartId = id;
//        this.products = new ArrayList<>();
//        this.value = 0;
//    }
//
//    public CartBuilder(int id, float value){
//        this.cartId = id;
//        this.products = new ArrayList<>();
//        this.value = value;
//    }
//
//    public CartBuilder(Cart cart){
//        this.cartId = cart.getId();
//        this.products = cart.getProducts();
//        this.value = cart.getValue();
//    }

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
