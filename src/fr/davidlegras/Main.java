package fr.davidlegras;

import fr.davidlegras.customer.AlreadySignedInException;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, AlreadySignedInException {
        MarketingService session = MarketingService.getMarketingService();

        session.shopping();
    }
}
