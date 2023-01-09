package org.shop.classes;

// chain of responsibility - part 1

public abstract class AbstractAuthorization {
    private AbstractAuthorization next;

    public static AbstractAuthorization link(AbstractAuthorization first, AbstractAuthorization... chain) {
        AbstractAuthorization head = first;
        for (AbstractAuthorization nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean check(String email, String password);

    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
