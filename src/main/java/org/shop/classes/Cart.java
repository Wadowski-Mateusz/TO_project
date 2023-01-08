package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Maybe change productsId to ArrayList<Protuct> and use ID only for save and init
 * @id same as user id
 * */
public class Cart implements Convertible {
    private int id;
    private float value;
    private ArrayList<Integer> productsId; // TODO change to <Product>

    // TODO change to builder (or maybe order should be construct by builder?)
    public Cart(int id){
       this.id = id;
       this.value = 0.0F;
       this.productsId = new ArrayList<>();
        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(!dbc.saveToFile(this)){
            System.out.println("Failed save to file");
        }
    }

    private Cart(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.value = Float.parseFloat(data[1]);
        this.productsId = new ArrayList<>();
        for(int i = 2; i < data.length; i++)
            productsId.add(Integer.parseInt(data[i]));
    }

    public int getId() {
        return id;
    }
    public void setValue(float value){
        this.value = value;
    }
    public float getValue(){
        return this.value;
    }

    // TODO
    public boolean addProduct(Product product){
        throw new UnsupportedOperationException("addProduct() not implemented");
    }
    // TODO
    public boolean removeProduct(int productId){
        throw new UnsupportedOperationException("removeProduct() not implemented");
    }

    public ArrayList<Integer> getProductsId() {
        return productsId;
    }

    public void setProductsId(ArrayList<Integer> productsId) {
        this.productsId = productsId;
    }

    @Override
    public String convertToRecord() {
        String str = "";
        str = String.valueOf(this.id) + ",";
        str += String.format("%.2f", this.value).replace(",",".");
        if (productsId.size() > 0) {
            str += "," + productsId.toString();
            str = str.replace(" ", "");
            str = str.replace("[", "");
            str = str.replace("]", "");
        }
        return str;
    }

    static public Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String[] data = db.recordFromFile(id, Cart.class).split(",");
        if(data.equals(null))
            return null;
        if(data.length == 2)
            return new Cart(Integer.parseInt(data[0]));
        return new Cart(data);
    }

    public int createOrder(){
        throw new UnsupportedOperationException("createOrder not implemented");
        // TODO
        // should return order ID
    }
}