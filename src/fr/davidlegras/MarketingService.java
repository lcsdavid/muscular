package fr.davidlegras;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerListener;
import fr.davidlegras.product.Hight_tech;
import fr.davidlegras.product.Livres;
import fr.davidlegras.product.Product;
import fr.davidlegras.serviceMarketing.Checkout;
import fr.davidlegras.serviceMarketing.NotAPromouvableProductException;
import fr.davidlegras.serviceMarketing.NotInBoundsReductionException;
import fr.davidlegras.serviceMarketing.ProductOffer;

import javax.swing.event.EventListenerList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MarketingService {

    private final EventListenerList listeners = new EventListenerList();
    private final Map<Product, Integer> fidelityPointPerProduct;

    private ArrayList<Product> produits;
    private Map<String, Product> listeCate;

    public MarketingService() {

        this("chemin par défault");

    }

    public MarketingService(String databasePath) {
        super();
        // TODO charger les exemples de membre du Staff, client inscrit sur le site, matrice des gains de points fidelités...
        produits = new ArrayList<Product>();
        listeCate = new HashMap<>();
        initProduct(produits);
        fidelityPointPerProduct = new HashMap<>();
    }

    private void addProduct(Product produit) {
        produits.add(produit);
        this.listeCate.put(produit.getCategorie(), produit);
    }

    /* initialmisations */
    //fonction de creation des produits
    private void initProduct(ArrayList<Product> produits) {
        addProduct(new Product(294, new Hight_tech(), "Switch"));
        addProduct(new Product(150, new Hight_tech(), "Wii_U"));
        addProduct(new Product(70, new Hight_tech(), "Manette"));
        addProduct(new Product(30, new Hight_tech(), "Pad"));
        addProduct(new Product(54, new Livres("Stéphane King", "01/07/1996"), "La ligne verte"));
        addProduct(new Product(64, new Livres("Stéphane King", "01/02/1999"), "La tempête du siècle"));

    }

    private void initCheckoutV1(Checkout checkout) {
        try {
            checkout.addOffer(new ProductOffer(50, produits.get(0)));
            //checkout.addOffer(new ProductOffer(20, produits.get(6)));
            //checkout.addOffer(new FlashOffer(30, produits));
        } catch (NotInBoundsReductionException NotInBoundsReductionException) {
            NotInBoundsReductionException.printStackTrace();
        } catch (NotAPromouvableProductException e) {
            e.printStackTrace();
        }
    }



    /* test d'existance*/

    public Product existingProduct(String name) {
        for (Product product : produits) {
            if (product.getName().equals(name))
                return product;
        }
        return null;
    }

    /*affichage */
    public void afficheProducts() {
        System.out.println("Liste des produits :\n");
        for (Product product : produits) {
            System.out.println(product.toString() + "\n");
        }
    }

    public void printProductsByCate() {
        System.out.println("Liste des produits par catégorie :\n");
        for (Map.Entry<String, Product> entry : listeCate.entrySet()) {
            System.out.println("-" + entry.getKey() + "\n");
            System.out.println("    " + entry.getValue().toString() + "\n");
        }
    }


    //fonction qui va gérer le client pendant son shopping
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

            System.out.println("Voulez-vous afficher la liste entière ou la liste par categories ?\n");
            System.out.println("0 pour quitter le magazin (vos achats seront alors perdu),1 pour la liste entière, 2 pour la liste par categories,  3 pour passer à la caisse\n");
            System.out.println("4 pour afficher l'etat actuel de votre panier\n");
            System.out.println("Pour ajouter un ou plusieurs produits a votre panier entrer le nom du produit");
            reponse = sc.next();
            switch (reponse) {
                case "0":
                    shopping = false;
                    break;
                case "1":
                    afficheProducts();
                    break;
                case "2":
                    printProductsByCate();
                    break;
                case "3":
                    System.out.println("Prix de base : " + customer.rawPrice() + " euros.\n" + "Prix a payer : " + checkout.getPrice(customer.getCart()) + " euros");
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
                    System.out.println("\nCette entrée n'est pas valide!!\n");
                    continue;
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
