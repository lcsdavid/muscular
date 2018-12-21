package fr.davidlegras;

import fr.davidlegras.customer.AlreadyConnectedException;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, AlreadyConnectedException {
        MarketingService session = MarketingService.getMarketingService();

        session.shopping();
    }
}
