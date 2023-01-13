package org.shop.classes;

import org.shop.interfaces.Convertible;

public class Tag implements Convertible {

    private volatile static int freeId = -1;
    private int id;
    private String name;

    public Tag(String name){
        this.name = name;

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(freeId < 0)
            freeId = dbc.findFreeId(Tag.class);
        this.id = freeId++;

        if(!dbc.saveToFile(this)){
            System.out.println("Failed save to file");
            this.id = -1;
            freeId -= 1;
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
        String record = db.loadData(id, Tag.class);
        if(record.isEmpty())
            System.exit(-1);
        String[] data = record.split(",");
        return new Tag(data);
    }

    public void update(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.updateRecord(this);
    }

    public void updateObject(){
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.loadData(id, Order.class);
//        if(record.isEmpty())
//            throw new //todo
        String[] data = record.split(",");
        this.name = data[1];

    }

}
