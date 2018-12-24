import fr.davidlegras.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static String textToSHA256(String text) {
        MessageDigest messageDigest = null;
        try { messageDigest = MessageDigest.getInstance("SHA-256"); }
        catch (NoSuchAlgorithmException ignored) { /* L'algorithme existe. */ }
        byte[] hash = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));
        String result = "";
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                result += '0';
            result += hex;
        }
        return result;
    }

    public static void test(Platform platform) {
          /* Fonction ou vous pouvez faire ce que vous voulez pour ajouter des produits, offres, etc... (en plus des notres)
         * En dehors de la construction par défaut de notre classe laFac.
         */
        try { /* exemples */
            /* Testez comme bon vous semble. */
            platform.addProduct(new Book("La Machine infernale", 8.60, 20, "Jean Cocteau", new Date(1934,4, 10)));
            platform.addOffer(new CategoryOffer(-0.1, Book.class));

            /* On considère que pour le test les données sont robustes donc pas de bêtises. */
            platform.server().createAccount("bogato", textToSHA256("coquillage"), "Quentin", "B", Member.class, new LoyaltyCard(), new LoyaltyCard(50));

        } catch (NegativePriceException e) {
            System.out.println("Vous avez trop jouer avec la fonction de test Monsieur, le prix est négatif !");
        } catch (NotDiscountableException e) {
            System.out.println("Vous avez trop jouer avec la fonction de test Monsieur, vous avez créé une offre sur une catégorie qui ne peut être en promo !");
        } catch (NotInBoundsDiscountException e) {
            System.out.println("Vous avez trop jouer avec la fonction de test Monsieur, votre réduction rend le produit gratuit ou le rend plus chère !");
        }
    }

    public static void main(String[] args) {
        laFac laFacDotCom = new laFac();

        Scanner sc = new Scanner(System.in);
        String response = null;

        Customer customer = laFacDotCom.customer();

        System.out.println("Bienvenue cher/chère client.e.s !\n");
        boolean end = false;
        while (!end) {
            /* Affichage des instructions. */
            System.out.println(customer.toString() + ", choisissez parmis les choix suivants:");
            if (!customer.isConnected()) System.out.println("[SeConnecter]: Se connecter à son profil personnel.");
            else System.out.println("[SeDéconnecter]: Se déconnecter de son profil personnel.");
            System.out.println("[Produits]: Afficher la liste des produits.");
            System.out.println("[Panier]: Pour afficher le panier.");
            System.out.println("[Payer]: Pour passer à la caisse.");
            System.out.println("[Quitter]: Quitter le magasin (vos achats seront alors perdu).");
            System.out.println();
            System.out.println("Pour ajouter un ou plusieurs produits à votre panier entrez le nom du produit.");

            /* Réponse du programme. */
            response = sc.next();
            for (int i = 0; i < 4; i++) System.out.println(); /* C'est moche je sais... */
            switch (response) {
                case "SeConnecter":
                    System.out.println("Entrez votre nom d'utilisateur : ");
                    String login = sc.next();
                    System.out.println("Entrez votre mot de passe : ");
                    try {
                        String passwordHash = textToSHA256(sc.next());
                        customer.connect(laFacDotCom, login, passwordHash);
                    } catch (AlreadyConnectedException e) {
                        System.out.println("Vous êtes déjà connecté... Déconnectez vous avant de pouvoir vous reconnecter.");
                    } catch (WrongCredentials e) {
                        System.out.println("Mauvais identifiant ou mot de passe...");
                    }
                    break;
                case "SeDéconnecter":
                    try {
                        customer.disconnect(laFacDotCom);
                    } catch (NotConnectedException e) {
                        System.out.println("Vous ne pouvez pas vous déconnecter, vous n'êtes pas connecter...");
                    }
                    break;
                case "Produits":
                    System.out.println(customer.toString() + ", choisissez parmis les choix suivants:");
                    System.out.println("(default) [Alpha] Trier par ordre alphabétique.");
                    System.out.println("[Catégorie] Trier par catégorie.");
                    switch (sc.next()) {
                        case "Catégorie":
                            ((ArrayList<Product>) laFacDotCom.products()).sort(Comparator.comparing(o -> o.getClass().getSimpleName()));
                            break;
                        default:
                            ((ArrayList<Product>) laFacDotCom.products()).sort(Comparator.comparing(Product::productTitle));
                            break;
                    }
                    break;
                case "Panier":
                    System.out.println("Panier :");
                    System.out.println(customer.cart().toString());
                    break;
                case "Payer":
                    System.out.println("Récap du panier :");
                    System.out.println(customer.cart().toString());
                    System.out.println("Ce que vous payer : " + customer.price(laFacDotCom));
                    break;
                case "Quitter":
                    end = true;
                    break;
                default:
                    Product toAddProduct = null;
                    for (Product product : laFacDotCom.products()) {
                        if (product.productTitle().equals(response)) {
                            toAddProduct = product;
                            break;
                        }
                    }
                    if (toAddProduct != null) {
                        System.out.println("Combien en voulez-vous ? (entre 1 et 9)");
                        int intResponse = sc.nextInt();
                        if (intResponse <= 9 && intResponse >= 1)
                            customer.addToCart(toAddProduct, intResponse);
                        System.out.println("Cette entrée n'est pas valide !");
                    }
                    break;
            }
            System.out.println();
        }
        System.out.println("Nous espérons vous revoir bientôt.");
    }
}
