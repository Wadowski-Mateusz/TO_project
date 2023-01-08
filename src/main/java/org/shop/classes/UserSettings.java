package org.shop.classes;

import org.shop.interfaces.Convertible;


/**
 * @id same as user id
 * @notificationAllow specify if user allows mails
 */
public class UserSettings implements Convertible{
    private int id;
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
        String result = id + "," + notificationAllow;
        return result;
    }

    static Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String[] data = db.recordFromFile(id, UserSettings.class).split(",");
        if(data.equals(null))
            return null;
        return new UserSettings(data);
    }

}
