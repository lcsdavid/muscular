package fr.davidlegras.customer;


import fr.davidlegras.MarketingService;

public class Visitor extends NotSignedInCustomer {
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
    public String getState() {
        return "Visitor";
    }

    @Override
    public int price(Customer customer) {
        return 0;
    }

    @Override
    public void signIn(final Customer context, final String login, final String passwordHash) throws WrongCredentials {
        if (!MarketingService.getMarketingService().loginExist(login))
            throw new WrongCredentials("User's login doesn't exist.");
        CustomerState state = MarketingService.getMarketingService().connect(login, passwordHash);
        if (state == null)
            throw new WrongCredentials("Wrong password.");
        context.customerState(state);
    }
}
