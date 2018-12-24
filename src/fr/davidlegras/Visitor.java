package fr.davidlegras.customer;

import fr.davidlegras.Platform;

public final class Visitor implements NotConnectedCustomer {
    private static Visitor UNIQUE_VISITOR_INSTANCE = null;

    private Visitor() {
        super();
    }

    public static Visitor getVisitor() {
        if (UNIQUE_VISITOR_INSTANCE == null)
            UNIQUE_VISITOR_INSTANCE = new Visitor();
        return UNIQUE_VISITOR_INSTANCE;
    }

    @Override
    public String toString() {
        return "Guest";
    }
}
