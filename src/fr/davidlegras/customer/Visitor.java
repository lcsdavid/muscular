package fr.davidlegras.customer;


import fr.davidlegras.MarketingService;
import fr.davidlegras.product.Discountable;
import fr.davidlegras.serviceMarketing.CommercialOffer;

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
    public int price(Customer customer) {
        for (CommercialOffer<Discountable> offer: MarketingService.getMarketingService().offers()) {
            offer.
        }

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
