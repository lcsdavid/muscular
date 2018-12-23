package fr.davidlegras;

import fr.davidlegras.customer.*;
import fr.davidlegras.product.Discountable;
import fr.davidlegras.product.HighTech;
import fr.davidlegras.product.Book;
import fr.davidlegras.product.Product;
import fr.davidlegras.product.CommercialOffer;
import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;
import fr.davidlegras.product.ProductOffer;
import javafx.util.Pair;

import javax.swing.event.EventListenerList;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class MarketingService {
    private static MarketingService UNIQUE_MARKETING_SERVICE_INSTANCE = null;

    /**
     * Tableau associatif dont la clé est le login d'un utilisateur inscrit sur le site,
     * la valeur associée une paire d'information: le hash du mot de passe correspondant et la classe du client (Member ou Staff).
     * Cette representation est fait de sorte à représenter au mieux une base de donnée externe sans une base de
     * de donnée tout en restant simple pour l'exemple.
     */
    private final Map<String, Pair<String, ConnectedCustomer>> users = new HashMap<>();
    /**
     * Liste des produits disponibles en magasin.
     * Chaque clé est un produit et sa valeur associé est le nombre de point de fidelité correspondant accordé à l'achat.
     * Attribut analogue ArrayList<Pair<Product, Integer>>.
     */
    private final List<Pair<Product, Integer>> products = new ArrayList<>();

    private List<CommercialOffer<Discountable>> offers = new ArrayList<>();

    /**
     * Construceur par défaut et privée pour contrôle l'unicité de l'instanciation de la classe.
     */
    private MarketingService() {
        super();
        initProducts();
        initUsers();
        initOffers();
    }

    public static MarketingService getMarketingService() {
        if (UNIQUE_MARKETING_SERVICE_INSTANCE == null)
            UNIQUE_MARKETING_SERVICE_INSTANCE = new MarketingService();
        return UNIQUE_MARKETING_SERVICE_INSTANCE;
    }

    public List<CommercialOffer<Discountable>> offers() {
        return offers;
    }

    /**
     * Initialisation d'exemples de produit proposés sur notre plateforme.
     */
    private void initProducts() {
        addProduct(new Product(294, new HighTech(), "Switch"), (int) (Math.random() * 5));
        addProduct(new Product(54, new Book("Stéphane King", new Date(1996, 7, 1)), "La ligne verte"), (int) (Math.random() * 5));
        addProduct(new Product(150, new HighTech(), "Wii_U"));
        addProduct(new Product(70, new HighTech(), "Manette"), (int) (Math.random() * 5));
        addProduct(new Product(30, new HighTech(), "Pad"), (int) (Math.random() * 5));
        addProduct(new Product(64, new Book("Stéphane King", new Date(1999, 2, 1)), "La tempête du siècle"));
    }

    /**
     * Initialisation d'exemples de clients utilisateurs et enregistés sur la plateforme.
     */
    private void initUsers() {
        users.put("lcsdavid", new Pair<>("967520ae23e8ee14888bae72809031b98398ae4a636773e18fff917d77679334", new Staff("Lucas")));      /* Le mot de passe c'est: motdepasse */
        users.put("GRUUUUUUU", new Pair<>("938d4bd2706c2a997ccdd47664edc9c31220788096ef35754ab4c517ceeeba52", new Staff("Théo")));      /* Le mot de passe c'est: iltapefort! */
        users.put("fv", new Pair<>("ee05aaa41f8af44fa40db1c523f6efbf6e6baba11ac6b84fb7150ddd486897ae", new Member("Frédéric")));        /* Le mot de passe c'est: alorsçacompile? */
    }

    private void initOffers() {

    }

    private void initCheckoutV1(Checkout checkout) {
        try {
            checkout.addOffer(new ProductOffer(50, products.get(0).getKey()));
            //checkout.attachOffer(new ProductOffer(20, products.get(6)));
            //checkout.attachOffer(new FlashOffer(30, products));
        } catch (NotInBoundsDiscountException | NotAPromouvableProductException e) {
            e.printStackTrace();
        }
    }

    /* Accesseurs */
    public Product existingProduct(String name) {
        for (Pair<Product, Integer> product : products) {
            if (product.getKey().name().equals(name))
                return product.getKey();
        }
        return null;
    }

    public boolean loginExist(String login) {
        return users.containsKey(login);
    }

    public ConnectedCustomer connect(String login, String passwordHash) {
        System.out.println(users.get(login).getKey() + '\n' + passwordHash);
        if (users.get(login).getKey().equals(passwordHash))
            return users.get(login).getValue();
        return null;
    }

    /* Mutateurs */
    private void addProduct(Product product) {
        addProduct(product, 0);
    }

    private void addProduct(Product product, int fidelityPoints) {
        products.add(new Pair<>(product, fidelityPoints));
    }

    /* Affichage */
    public void printProducts() {
        for (Pair<Product, Integer> product : products)
            System.out.println(product.getKey().toString());
    }

    public void printProducts(Comparator<? super Product> c) {
        products.sort((o1, o2) -> c.compare(o1.getKey(), o2.getKey()));
        printProducts();
    }

    /* Interaction */

    /**
     * Routine d'affichage et d'interaction via la console.
     */
    public void shopping() throws NoSuchAlgorithmException {
        boolean shopping = true;

        String response;
        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        Checkout checkout = Checkout.getCheckout();
        initCheckoutV1(checkout);

        System.out.println("Bienvenue cher/chère client.e.s !");
        while (shopping) {
            /* Affichage des instructions. */
            System.out.println(customer.toString() + ", voulez-vous afficher la liste entière ou la liste par categories ?\n");
            System.out.println("[0]: Quitter le magasin (vos achats seront alors perdu).");
            System.out.println("[1]: Liste des produits.");
            System.out.println("[2]: Liste des produits par categorie.");
            System.out.println("[3]: Passer à la caisse.");
            System.out.println("[4]: Afficher l'état actuel de votre panier.");
            System.out.println();
            if (!customer.isConnected())
                System.out.println("[SignIn]: Se connecter son profil personnel.");
            else
                System.out.println("[SignOut]: Se déconnecter de son profil personnel.");
            System.out.println();
            System.out.println("Pour ajouter un ou plusieurs produits à votre panier entrez le nom du produit.");

            /* Réponse du programme. */
            response = sc.next();
            for (int i = 0; i < 50; i++) System.out.println(); /* C'est moche je sais... */
            switch (response) {
                case "0":
                    shopping = false;
                    break;
                case "1":
                    System.out.println("Liste des produits :");
                    printProducts();
                    break;
                case "2":
                    System.out.println("Liste des produits par catégorie :");
                    printProducts(Comparator.comparing(Product::category));
                    break;
                case "3":
                    System.out.println("Prix de base : " + customer.rawPrice() + "€.");
                    System.out.println("Prix à payer : " + checkout.getPrice(customer.cart()) + "€.");
                    shopping = false;
                    break;
                case "4":
                    System.out.println("Panier :\n\n" + customer.cartToString() + "\n\n");
                    break;
                case "SignIn":
                    String login, passwordHash = "";
                    System.out.println("Entrez votre nom d'utilisateur : ");
                    login = sc.next();
                    /* Sécurité */
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = messageDigest.digest(sc.next().getBytes(StandardCharsets.UTF_8));
                    for (byte b : hash) {
                        String hex = Integer.toHexString(0xff & b);
                        if (hex.length() == 1)
                            passwordHash += '0';
                        passwordHash += hex;
                    }
                    /* Connexion */
                    try {
                        customer.connect(null ,login, passwordHash);
                    } catch (AlreadyConnectedException e) {
                        System.out.println("Vous êtes déjà connecté... Déconnectez vous avant de pouvoir vous reconnecter.");
                    } catch (WrongCredentials wrongCredentials) {
                        System.out.println(wrongCredentials.getMessage());
                    }
                    break;
                case "SignOut":
                    try {
                        customer.disconnect(null);
                    } catch (NotConnectedException e) {
                        System.out.println("Vous ne pouvez pas vous déconnecter, vous n'êtes pas connecter...");
                    }
                    break;
                default:
                    Product res = existingProduct(response);
                    if (res != null) {
                        System.out.println("Combien en voulez-vous ? (entre 1 et 9)");
                        response = sc.next();
                        if (response.charAt(0) <= '9' && response.charAt(0) >= '1' && response.length() == 1) {
                            customer.addToCart(res, response.charAt(0) - '0');
                            continue;
                        }
                    }
                    System.out.println("Cette entrée n'est pas valide !!");
                    break;
            }
            System.out.println();
        }
        System.out.println("Nous espérons vous revoir bientôt.");
    }

    private final EventListenerList listeners = new EventListenerList();


    /**
     * Classe de passage à la caisse.
     */
    private static class Checkout {
        private static Checkout UNIQUE_CHECKOUT_INSTANCE = new Checkout();
        private ArrayList<CommercialOffer> offers;

        private Checkout() {
            offers = new ArrayList<>();
        }
        /* fin de la déclaration du singleton */

        public static Checkout getCheckout() {
            return UNIQUE_CHECKOUT_INSTANCE;
        }

        public float checkout(Map<Product, Integer> cart) {
            float res = 0;

            return res;
        }

        public void addOffer(CommercialOffer offer) {
            offers.add(offer);
        }

        private float getReduction(Map<Product, Integer> cart) {
            float res = 0;
            for (CommercialOffer offer : offers) {
                res += offer.discount();
            }
            return res;
        }

        public float getPrice(Map<Product, Integer> cart) {
            float res = 0;
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                res += entry.getKey().price() * entry.getValue();
            }
            //TODO ajouter exception pour le cas ou la prix est 0

            res -= getReduction(cart);

            //si les réductions sont plus grandes que le prix on remène le prix à 0
            //ce cas n'est pas concidéré comme une erreure car on accepte le cumul des réductions.
            if (res <= 0)
                res = 0;

            return res;
        }
    }
}