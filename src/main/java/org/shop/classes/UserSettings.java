package org.shop.classes;

import org.shop.interfaces.Convertible;


/**
 * id same as user id
 * notificationAllow specify if user allows mails
 */
public class UserSettings implements Convertible{
    private final int id;
    private boolean notificationAllow;

    public UserSettings(int id){
        this.id = id;
        this.notificationAllow = false;
    }

    private UserSettings(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.notificationAllow = Boolean.parseBoolean(data[1]);

        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(!dbc.saveToFile(this)){
            System.out.println("Failed save to file");
        }
    }

    public int getId() {
        return id;
    }

    public boolean getNotificationAllow() {
        return notificationAllow;
    }
    public void setNotificationAllow(boolean notificationAllow) {
        this.notificationAllow = notificationAllow;
    }

    @Override
    public String convertToRecord(){
        return id + "," + notificationAllow;
    }

    static Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.recordFromFile(id, UserSettings.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new UserSettings(data);
    }

}
