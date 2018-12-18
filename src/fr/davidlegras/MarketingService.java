package fr.davidlegras;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerListener;
import fr.davidlegras.product.HighTech;
import fr.davidlegras.product.Livres;
import fr.davidlegras.product.Product;
import fr.davidlegras.serviceMarketing.Checkout;
import fr.davidlegras.serviceMarketing.NotAPromouvableProductException;
import fr.davidlegras.serviceMarketing.NotInBoundsReductionException;
import fr.davidlegras.serviceMarketing.ProductOffer;

import javax.swing.event.EventListenerList;
import java.util.*;


public class MarketingService {

    private final EventListenerList listeners = new EventListenerList();
    private final Map<Product, Integer> fidelityPointPerProduct;

    private ArrayList<Product> products;
    /* private Map<String, Product> listeCate; Plus besoin pour l'affichage par catégorie il ya plus simple. */

    public MarketingService() {

        this("chemin par défault");

    }

    public MarketingService(String databasePath) {
        super();
        // TODO charger les exemples de membre du Staff, client inscrit sur le site, matrice des gains de points fidelités...
        products = new ArrayList<Product>();

        initProduct(products);
        fidelityPointPerProduct = new HashMap<>();
    }

    private void addProduct(Product produit) {
        products.add(produit);
    }

    /* Initialisation pour test */
    private void initProduct(ArrayList<Product> produits) {
        addProduct(new Product(294, new HighTech(), "Switch"));
        addProduct(new Product(54, new Livres("Stéphane King", "01/07/1996"), "La ligne verte"));
        addProduct(new Product(150, new HighTech(), "Wii_U"));
        addProduct(new Product(70, new HighTech(), "Manette"));
        addProduct(new Product(30, new HighTech(), "Pad"));
        addProduct(new Product(64, new Livres("Stéphane King", "01/02/1999"), "La tempête du siècle"));

    }

    private void initCheckoutV1(Checkout checkout) {
        try {
            checkout.addOffer(new ProductOffer(50, products.get(0)));
            //checkout.addOffer(new ProductOffer(20, products.get(6)));
            //checkout.addOffer(new FlashOffer(30, products));
        } catch (NotInBoundsReductionException | NotAPromouvableProductException e) {
            e.printStackTrace();
        }
    }



    /* Test d'existance */

    public Product existingProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name))
                return product;
        }
        return null;
    }

    /* Affichage */
    public void printProducts() {
        for (Product product : products)
            System.out.println(product.toString());
    }

    public void printProducts(Comparator<? super Product> c) {
        products.sort(c);
        printProducts();
        /* for (Map.Entry<String, Product> entry : listeCate.entrySet()) {
            System.out.println("-" + entry.getKey() + "\n");
            System.out.println("    " + entry.getValue().toString() + "\n");
        } */
    }


    /**
     * Routine d'affichage et d'interaction via la console.
     */
    public void shopping() {
        boolean shopping = true;
        Checkout checkout = Checkout.getCheckout();
        initCheckoutV1(checkout);
        Customer customer = new Customer();
        String reponse = "0";
        Scanner sc = new Scanner(System.in);
        System.out.println("\nBienvenue cher client !\n");
        while (shopping) {

            //TODO gerer la connexion du client

            /* Affichage des instructions. */
            System.out.println("Voulez-vous afficher la liste entière ou la liste par categories ?\n");
            System.out.println("[0]: Quitter le magasin (vos achats seront alors perdu).");
            System.out.println("[1]: Liste des produits.");
            System.out.println("[2]: Liste des produits par categorie.");
            System.out.println("[3]: Passer à la caisse.");
            System.out.println("[4]: Afficher l'état actuel de votre panier.");
            System.out.println("Pour ajouter un ou plusieurs produits à votre panier entrez le nom du produit.");

            /* Réponse du programme. */
            reponse = sc.next();
            switch (reponse) {
                case "0":
                    shopping = false;
                    break;
                case "1":
                    System.out.println("Liste des produits :");
                    printProducts();
                    break;
                case "2":
                    System.out.println("Liste des produits par catégorie :");
                    printProducts(Comparator.comparing(Product::getCategory));
                    break;
                case "3":
                    System.out.println("Prix de base : " + customer.rawPrice() + "€.");
                    System.out.println("Prix à payer : " + checkout.getPrice(customer.getCart()) + "€.");
                    shopping = false;
                    break;
                case "4":
                    System.out.println("Panier :\n\n" + customer.cartToString() + "\n\n");
                    break;
                default:
                    Product res = existingProduct(reponse);
                    if (res != null) {
                        System.out.println("Combien en voulez-vous ? (entre 1 et 9)\n");
                        reponse = sc.next();
                        if (reponse.charAt(0) <= '9' && reponse.charAt(0) >= '1' && reponse.length() == 1) {
                            customer.addToCart(res, reponse.charAt(0) - '0');
                            continue;
                        }
                    }
                    System.out.println("\nCette entrée n'est pas valide !!\n");
                    break;
            }

        }

        System.out.println("Nous espérons vous revoir bientôt.\n");
    }

    public synchronized void addCustomerListener(CustomerListener listener) {
        listeners.add(CustomerListener.class, listener);
    }

    public synchronized void removeCustomerListener(CustomerListener listener) {
        listeners.remove(CustomerListener.class, listener);
    }

}
