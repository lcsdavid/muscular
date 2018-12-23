package fr.davidlegras;

import fr.davidlegras.product.Offer;
import fr.davidlegras.product.Product;

import java.util.ArrayList;
import java.util.Collection;

public class laFac implements Platform {
    private static laFacServer SERVER = new laFacServer();

    private Collection<Product> products = new ArrayList<>();
    private Collection<Offer> offers = new ArrayList<>();

    @Override
    public Collection<Product> products() {
        return products;
    }

    @Override
    public Collection<Offer> offers() {
        return offers;
    }

    @Override
    public Server server() {
        return null;
    }

    static class laFacServer implements Platform.Server {
        /* Représentation des informations de laFac.com comme si elle était vraiment externes. */
        /**
         * Contient le table pseudo-SQL suivant:
         * |name| | lastName | type | informationsSuplémentairesEnFonctionDuType...
         */
        private ArrayList<String[]> customers = new ArrayList<>();

        /**
         * Contient le table pseudo-SQL suivant:
         * |prodcuctTitle| | prix | gainInLoyaltyPoints | type | informationsSuplémentairesEnFonctionDuType...
         */
        private ArrayList<String[]> products = new ArrayList<>();

        /**
         *
         */
        private ArrayList<String[]> offers = new ArrayList<>();

        public laFacServer() {
            customers.add(new String[]{""});

        }

        @Override
        public void onConnect(String login, String passwordHash) {

        }

        @Override
        public void onDisconnect() {

        }
    }
}
