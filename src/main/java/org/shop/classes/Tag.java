package org.shop.classes;

import org.shop.interfaces.Convertible;

public class Tag implements Convertible {

    private static int freeId = -1;
    private int id;
    private String name;

    public Tag(String name){
        this.name = name;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(this.freeId < 0)
            this.freeId = dbc.findFreeId(Tag.class);
        this.id = freeId++;

        if(!dbc.saveToFile(this)){
            System.out.println("Failed save to file");
            this.id = -1;
            this.freeId -= 1;
        }
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

    static Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String[] data = db.recordFromFile(id, Tag.class).split(",");
        if(data.equals(null))
            return null;
        return new Tag(data);
    }
}
