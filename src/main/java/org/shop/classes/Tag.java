package org.shop.classes;

import org.shop.interfaces.Convertible;

public class Tag implements Convertible {

    private int id;
    private String name;

    public Tag(){
        // todo
        throw new UnsupportedOperationException();
    }

    private Tag(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String convertToRecord(){
        return this.id + "," + this.name;
    }

    static Convertible convertFromRecord(String record) {
        String[] data = record.split(",");
        return new Tag(data);
    }
}
