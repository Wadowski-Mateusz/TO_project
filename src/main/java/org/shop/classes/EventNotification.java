package org.shop.classes;

// TODO make it convertible
public class EventNotification {

    private volatile static int freeId = -1;
    private int id;
    private String message;

    public EventNotification(String message){
        throw new UnsupportedOperationException("EventNotification() not implemented");
//        DatabaseConnector dbc = DatabaseConnector.getInstance();
//        if(this.freeId < 0)
//            this.freeId = dbc.findFreeId(.class);
//
//        this.id = freeId++;
//
//        if(!dbc.saveToFile(this)){
//            System.out.println("Failed save to file");
//            this.id = -1;
//            this.freeId -= 1;
//        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
