package fr.davidlegras.customer;

public class MemberFactory implements CustomerStateFactory {

    private static CustomerState UNIQUE_MEMBER_INSTANCE = null;

    @Override
    public CustomerState makeCustomerState() {
        if (UNIQUE_MEMBER_INSTANCE == null)
            UNIQUE_MEMBER_INSTANCE = new Member();
        return UNIQUE_MEMBER_INSTANCE;
    }
}
