package org.shop.classes;

public class RegisteredUserChecker extends UserChecker {
    public RegisteredUserChecker(UserChecker next) {
        super(next);
    }

    @Override
    public boolean check(User user) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        if (db.verificationUserLoginData(user.getEmail(), user.getPassword()) > 0) {
            return true;
        }
        if (next != null) {
            return next.check(user);
        }
        return false;
    }
}
