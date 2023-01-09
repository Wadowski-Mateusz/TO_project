package org.shop.classes;

public class RoleAuthorization extends AbstractAuthorization {
    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("This is an admin.");
            return true;
        }
        System.out.println("This is a user.");
        return checkNext(email, password);
    }
}
