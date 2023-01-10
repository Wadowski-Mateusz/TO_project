package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;

/**
 * USE BUILDER FOR CREATING CART (pretty please with sugar on top)
 * id same as user id
 * */
public class Cart implements Convertible {
    private final int id;
    private float value;
    private ArrayList<Product> products;

    public Cart(int id){
        this.id = id;
        this.value = 0.0F;
        this.products = new ArrayList<>();

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(!dbc.saveToFile(this))
            System.out.println("Saving to file failed");

    }

    public Cart(int id, float value, ArrayList<Product> products){
        this.id = id;
        this.value = value;
        this.products = products;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(!dbc.saveToFile(this))
            System.out.println("Failed save to file");
    }


    public static CartBuilder getBuilder(){
        return new CartBuilder();
    }

    public int getId() {
        return id;
    }

    public float getValue(){
        return this.value;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        this.value = 0;
        products.forEach((Product p) -> this.value += p.getPrice());
    }

    @Override
    public String convertToRecord() {
        String record = this.id + ",";
        record += String.format("%.2f", this.value).replace(",",".");
        for(Product p : products)
            record += "," + p.getId();

        return record;
    }

    static public Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadFromFile(id, Cart.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");

        CartBuilder builder = Cart.getBuilder()
                .setId(Integer.parseInt(data[0]));

        for(int i = 2; i < data.length; i++)
            builder.addProduct(
                    (Product) Product.convertFromRecord(
                            Integer.parseInt(data[i])
                    )
            );

        return builder.build();
    }

    public Order createOrder(){
        Order order = new Order(this.value, new ArrayList<>(this.products));
        order.setShipping(new Shipping(order.getId(), (Address) Address.convertFromRecord(this.id)));
        this.value = 0.0F;
        this.products.clear();

        return order;
    }
}