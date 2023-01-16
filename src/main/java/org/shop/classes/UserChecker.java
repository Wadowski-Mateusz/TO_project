package org.shop.classes;

public abstract class UserChecker {
    protected UserChecker next;

    public UserChecker(UserChecker next) {
        this.next = next;
    }

    public abstract boolean check(User user);
}