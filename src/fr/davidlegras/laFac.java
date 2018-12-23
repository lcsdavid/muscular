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
        /* Représentation des informations de laFac.com comme si elles étaient vraiment externes. */
        /**
         * Contient le table pseudo-SQL suivant:
         * [login] | passwordHash | name | lastName | type | informationsSuplémentairesEnFonctionDuType...
         */
        private ArrayList<String[]> customers = new ArrayList<>();
        /**
         * Contient le table pseudo-SQL suivant:
         * [prodcuctTitle] | prix | gainInLoyaltyPoints | type | informationsSuplémentairesEnFonctionDuType...
         */
        private ArrayList<String[]> products = new ArrayList<>();
        /**
         * Contient le table pseudo-SQL suivant:
         * [discount | customerType | type | informationsSuplémentairesEnFonctionDuType...
         */
        private ArrayList<String[]> offers = new ArrayList<>();

        public laFacServer() {
            /* Ajout d'exemple de CustomerState. */
            customers.add(new String[]{"lcsdavid", "967520ae23e8ee14888bae72809031b98398ae4a636773e18fff917d77679334", "Lucas", "David", "Staff"});   /* Le mot de passe c'est: motdepasse */
            customers.add(new String[]{"GRUUUUUUU", "938d4bd2706c2a997ccdd47664edc9c31220788096ef35754ab4c517ceeeba52", "Théo", "Legras", "Staff"});  /* Le mot de passe c'est: iltapefort! */
            customers.add(new String[]{"fv", "ee05aaa41f8af44fa40db1c523f6efbf6e6baba11ac6b84fb7150ddd486897ae", "Frédéric", "Voisin", "Member"});    /* Le mot de passe c'est: alorsçacompile? */

            /* Ajout d'exemple de Product. */
            products.add(new String[]{"La Ligne verte Poche – 23 avril 2008", "8.30", "2", "Book", "La Ligne verte", "Stephen King", "23/04/2008"});
            products.add(new String[]{"Nintendo Switch", "294", "8", "HighTech"});
            products.add(new String[]{"Nintendo Wii U", "150", "5", "HighTech"});
            products.add(new String[]{"Nintendo Switch Joy Con", "66.85", "3", "HighTech"});
            products.add(new String[]{"Nintendo Wii U Pad", "42", "2", "HighTech"});

            /* Ajout d'exemple de Offer. */
            offers.add(new String[]{"-50", "CustomerState", "ProductOffer", "La Ligne verte Poche – 23 avril 2008"}); /* Ne doit pas fonctionner. */
            offers.add(new String[]{"-10", "CustomerState", "CategoryOffer", "Book"}); /* Ne dois pas fonctionner. */
            offers.add(new String[]{"-80", "Staff", "FlashOffer", "Nintendo Switch", "1", "Nintendo Switch Joy Con", "2"});
        }

        @Override
        public void onConnect(String login, String passwordHash) {

        }

        @Override
        public void onDisconnect() {

        }
    }
}
