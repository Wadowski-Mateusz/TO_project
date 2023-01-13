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
        String record = db.loadData(id, Cart.class);
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

    public void update(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.updateRecord(this);
    }

    public Order createOrder(){

        for(Product p : products)
            if (p.getHowManyStock() > 0)
                if(p.getVisibility())
                    p.setHowManyStock(p.getHowManyStock() - 1);
                else
                    throw new UnsupportedOperationException(); // TODO przedmiot juz nie jest widzialny i możliwy do kupienia
            else
                throw new UnsupportedOperationException(); // TODO przedmiotu juz nie ma na stanie


        Order order = new Order(this.value, new ArrayList<>(this.products));
        order.setShipping(new Shipping(order.getId(), (Address) Address.convertFromRecord(this.id)));
        this.value = 0.0F;
        this.products.clear();

        DatabaseConnector dbc = DatabaseConnector.getInstance();

        if(!dbc.saveToFile(order))
            System.out.println("Saving to file failed");

        return order;
    }

    public void updateObject(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadData(id, Order.class);
//        if(record.isEmpty())
//            throw new //todo
        String[] data = record.split(",");

        this.products.clear();
        this.value = 0.0F;

        for(int i = 2; i < data.length; i++) {
            Product p = (Product) Product.convertFromRecord(
                    Integer.parseInt(data[i]));
            products.add(p);
            this.value += p.getPrice();
        }

    }

}