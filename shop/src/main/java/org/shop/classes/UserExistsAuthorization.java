package org.shop.classes;

import org.shop.app.ServerApp;

public class UserExistsAuthorization extends AbstractAuthorization {
    private Database database;

    public UserExistsAuthorization(Server server) {
        this.database = server;
    }

    public boolean check(String email, String password) {
        if (!database.hasEmail(email)) {
            System.out.println("This email is not registered.");
            return false;
        }
        if (!database.isValidPassword(email, password)) {
            System.out.println("Wrong password.");
            return false;
        }
        return checkNext(email, password);
    }
}
