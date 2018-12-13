package fr.davidlegras.customer;

public class StaffFactory implements CustomerStateFactory {

    private static CustomerState UNIQUE_STAFF_INSTANCE = null;

    @Override
    public CustomerState makeCustomerState() {
        if (UNIQUE_STAFF_INSTANCE == null)
            UNIQUE_STAFF_INSTANCE = new Staff("");
        // TODO name
        return UNIQUE_STAFF_INSTANCE;
    }
}
