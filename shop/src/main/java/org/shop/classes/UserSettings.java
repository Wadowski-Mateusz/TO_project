package org.shop.classes;

public class UserSettings {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNotificationAllow() {
        return notificationAllow;
    }

    public void setNotificationAllow(boolean notificationAllow) {
        this.notificationAllow = notificationAllow;
    }

    private boolean notificationAllow;

}
