package org.shop.classes;

import org.shop.interfaces.Convertible;

/**
 * id same as user id
 * */
public class UserSettings implements Convertible{
    private int id;
    private boolean notificationAllow;

    public UserSettings(int id){
        this.id = id;
        notificationAllow = false;
    }

    private UserSettings(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.notificationAllow = Boolean.parseBoolean(data[1]);

    }

    public int getId() {
        return id;
    }

    public boolean isNotificationAllow() {
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

    static Convertible convertFromRecord(String record) {
        String[] data = record.split(",");
        return new UserSettings(data);
    }

}
