package org.shop.classes;

public class RoleChecker extends UserChecker {
    public RoleChecker(UserChecker next) {
        super(next);
    }
    @Override
    public boolean check(User user) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        if (db.isUserAdmin(user.getEmail(), user.getPassword())) {
            return true;
        }
        if (next != null) {
            return next.check(user);
        }
        return false;
    }
}
