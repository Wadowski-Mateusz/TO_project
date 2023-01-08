package org.shop.classes;


import java.util.ArrayList;

public class CartBuilder {
    private final int cartId;
    private final ArrayList<Product> products;
    private float value;

    public CartBuilder(int id){
        this.cartId = id;
        this.products = new ArrayList<>();
        this.value = 0;
    }

    public CartBuilder(int id, float value){
        this.cartId = id;
        this.products = new ArrayList<>();
        this.value = value;
    }

    public CartBuilder(Cart cart){
        this.cartId = cart.getId();
        this.products = cart.getProducts();
        this.value = cart.getValue();
    }

    public void addProduct(Product product){
        this.products.add(product);
        this.value += product.getPrice();
    }

    public void removeProduct(Product product){
        for (int i = 0; i < this.products.size(); i++)
            if(products.get(i) == product) {
                this.products.remove(i);
                    break;
            }
        this.value -= product.getPrice();
    }

    public Cart build(){
        return new Cart(this.cartId, this.value, this.products);
    }

}
