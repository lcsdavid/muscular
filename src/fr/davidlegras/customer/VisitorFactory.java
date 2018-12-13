package fr.davidlegras.customer;

public class VisitorFactory implements CustomerStateFactory {

    private static CustomerState UNIQUE_VISITOR_INSTANCE = null;

    @Override
    public CustomerState makeCustomerState() {
        if (UNIQUE_VISITOR_INSTANCE == null)
            UNIQUE_VISITOR_INSTANCE = new Visitor();
        return UNIQUE_VISITOR_INSTANCE;
    }
}
