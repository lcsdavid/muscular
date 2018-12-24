package fr.davidlegras;

import fr.davidlegras.customer.*;
import fr.davidlegras.product.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class laFac implements Platform {
    private static laFacServer SERVER = new laFacServer();

    private Collection<Product> products = new ArrayList<>();
    private Collection<Offer> offers = new ArrayList<>();

    public laFac() {
        /* Ajout d'exemple de Product. */
        try {
            products.add(new Book("La Ligne verte Poche – 23 avril 2008", 8.30, 2, "Stephen King", new Date(2008, 4, 23)));
            products.add(new HighTech("Nintendo Switch", 294, 8));
            products.add(new HighTech("Nintendo Wii U", 150, 5));
            products.add(new HighTech("Nintendo Switch Joy Con", 66.85, 3));
            products.add(new HighTech("Nintendo Wii U Pad", 42, 2));

            /* Ajout d'exemple de Offer. */
            offers.add(new ProductOffer(-0.5, ((ArrayList<Product>) products).get(0)));
            Cart cart = new Cart();
            cart.add(((ArrayList<Product>) products).get(1), 1); /* Nintendo Switch */
            cart.add(((ArrayList<Product>) products).get(1), 2); /* Nintendo Switch Joy Con */
            offers.add(new FlashOffer(-0.2, Staff.class, cart));
        } catch (Exception ignored) {
            /* On a pas de prix négatif là. */
            /* On a pas de mauvaise réduction ici. */
        }
    }

    @Override
    public Collection<Product> products() {
        return products;
    }

    @Override
    public Collection<Offer> offers() {
        return offers;
    }

    @Override
    public CustomerState connect(String login, String passwordHash) throws WrongCredentials {
        String[] customer = server().connect(login, passwordHash);
        try {
            Class<?> stateClass = Class.forName("Visitor");
            Constructor<?> constructor = stateClass.getConstructors()[0];
            /* On fait les Menbre comme ça juste pour le test. Mais l'implémentation dépendra du support. */
            LoyaltyCard[] card = new LoyaltyCard[customer.length - 5];
            for (int i = 5; i < customer.length; i++)
                card[i - 5] = new LoyaltyCard(Integer.parseInt(customer[i]));
            return (CustomerState) constructor.newInstance(customer[2], customer[3], card);
        } catch (Exception ignored) {
            /* On considère que pour le test les données sont robustes. */
            ignored.printStackTrace();
        }
        return null;
    }

    @Override
    public void disconnect() {

    }

    @Override
    public Server server() {
        return SERVER;
    }

    /**
     * Representation/simultation d'un serveur distant. Implémentation de test qui ne représente pas
     * la réalité. Ce réferer à l'interface {@code Platform} et {@code Platform.Server}.
     */
    static class laFacServer implements Platform.Server {
        /* Représentation des informations de laFac.com comme si elles étaient vraiment externes. */
        /**
         * Contient le table pseudo-SQL suivant:
         * [login] | passwordHash | name | lastName | type | informationsSuplémentairesEnFonctionDuType...
         */
        private Collection<String[]> customers = new ArrayList<>();


        public laFacServer() {
            /* Ajout d'exemple de CustomerState. */
            customers.add(new String[]{"lcsdavid", "967520ae23e8ee14888bae72809031b98398ae4a636773e18fff917d77679334", "Lucas", "David", "Staff"});   /* Le mot de passe c'est: motdepasse */
            customers.add(new String[]{"GRUUUUUUU", "938d4bd2706c2a997ccdd47664edc9c31220788096ef35754ab4c517ceeeba52", "Théo", "Legras", "Staff"});  /* Le mot de passe c'est: iltapefort! */
            customers.add(new String[]{"fv", "ee05aaa41f8af44fa40db1c523f6efbf6e6baba11ac6b84fb7150ddd486897ae", "Frédéric", "Voisin", "Member"});    /* Le mot de passe c'est: alorsçacompile? */
        }

        @Override
        public String[] queryCustomer(String login) {
            String[] customer = null;
            for (String[] customerStrings: customers)
                if (customerStrings[0].equals(login)) {
                    customer = customerStrings;
                    break;
                }
            return customer;
        }

        @Override
        public String[] connect(String login, String passwordHash) throws WrongCredentials {
            String[] customer = queryCustomer(login);
            if (customer == null || !customer[1].equals(passwordHash))
                throw new WrongCredentials();
            return customer;
        }

        @Override
        public void disconnect() {}
    }
}
