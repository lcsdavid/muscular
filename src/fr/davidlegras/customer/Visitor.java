package fr.davidlegras.customer;


import fr.davidlegras.MarketingService;
import fr.davidlegras.product.Discountable;
import fr.davidlegras.product.CommercialOffer;
import javafx.application.Platform;

public final class Visitor implements NotConnectedCustomer {
    private static Visitor UNIQUE_VISITOR_INSTANCE = null;

    private Visitor() {
        super();
    }

    static Visitor getVisitor() {
        if (UNIQUE_VISITOR_INSTANCE == null)
            UNIQUE_VISITOR_INSTANCE = new Visitor();
        return UNIQUE_VISITOR_INSTANCE;
    }

    @Override
    public int price(Platform platform, Customer customer) {
        for (CommercialOffer<Discountable> offer: MarketingService.getMarketingService().offers()) {


        }

        return 0;
    }
}
