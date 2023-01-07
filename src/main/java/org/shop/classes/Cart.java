package org.shop.classes;

import org.shop.interfaces.Convertible;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Maybe change productsId to ArrayList<Protuct> and use ID only for save and init
 * */
public class Cart implements Convertible {
    private int id;
    private float value;
    private ArrayList<Integer> productsId;

    /** TODO change to builder (or maybe order should be made by builder?)
     * @param id same as User id
     * */
    public Cart(int id){
       this.id = id;
       this.value = 0.0F;
       this.productsId = new ArrayList<>();
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

    public boolean addProduct(){
        throw new UnsupportedOperationException();
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

    static public Convertible convertFromRecord(String record) {
        String[] data = record.split(",");
        if(data.length == 2)
            return new Cart(Integer.parseInt(data[0]));
        return new Cart(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id && Float.compare(cart.value, value) == 0 && Objects.equals(productsId, cart.productsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, productsId);
    }

    public int createOrder(){
        throw new UnsupportedOperationException();
        // TODO
        // should return order ID
    }
}