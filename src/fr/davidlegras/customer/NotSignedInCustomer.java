package fr.davidlegras.customer;

import javafx.application.Platform;

public interface NotSignedInCustomer extends CustomerState {

    @Override
    default void signIn(final Platform platform, final Customer context, String login, String passwordHash) {
        /*if (!MarketingService.getMarketingService().loginExist(login))
            throw new WrongCredentials("User's login doesn't exist.");
        CustomerState state = MarketingService.getMarketingService().connect(login, passwordHash);
        if (state == null)
            throw new WrongCredentials("Wrong password.");
        context.customerState(state);*/
    }

    @Override
    default void signOut(final Platform unusedPlatform, final Customer unusedCustomer) throws NotSignedInException {
        throw new NotSignedInException();
    }
}
