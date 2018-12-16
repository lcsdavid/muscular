package fr.davidlegras;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerListener;
import fr.davidlegras.product.Product;
import fr.davidlegras.serviceMarketing.Checkout;
import fr.davidlegras.serviceMarketing.FlashOffer;
import fr.davidlegras.serviceMarketing.ProductOffer;
import fr.davidlegras.serviceMarketing.notInBoundsReduction;

import javax.swing.event.EventListenerList;
import java.io.IOException;
import java.net.SocketOption;
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

    private void addProduct(Product produit){
        produits.add(produit);
        this.listeCate.put(produit.getCategorie(), produit);
    }

    /* initialmisations */
    //fonction de creation des produits
    private void initProduct(ArrayList<Product> produits){
        addProduct(new Product(294, "Jeux vidéos", "Switch"));
        addProduct(new Product(150, "Jeux vidéos", "Wii_U"));
        addProduct(new Product(70, "Jeux vidéos", "Manette"));
        addProduct(new Product(30, "Jeux vidéos", "Pad"));
        addProduct(new Product(10, "Fromage", "Comté"));
        addProduct(new Product(24, "Fromage", "Saint-nectaire"));
        addProduct(new Product(94, "Fromage", "Brie"));
        addProduct(new Product(54, "Fromage", "Camembert"));
        addProduct(new Product(42, "Fromage", "Roblochon"));
        addProduct(new Product(54, "Viande", "Filet_de_boeuf"));
        addProduct(new Product(64, "Viande", "Araignée"));

    }

    private void initCheckoutV1(Checkout checkout){
        try {
            checkout.addOffer(new ProductOffer(50, "Switch"));
            checkout.addOffer(new ProductOffer(20, "Brie"));
            checkout.addOffer(new FlashOffer(30, produits));
        } catch (fr.davidlegras.serviceMarketing.notInBoundsReduction notInBoundsReduction) {
            notInBoundsReduction.printStackTrace();
        }
    }



    /* test d'existance*/

    public Product existingProduct(String name){
        for (Product product:produits) {
            if(product.getName().equals(name))
                return product;
        }
        return null;
    }

    /*affichage */
    public void afficheProducts(){
        System.out.println("Liste des produits :\n");
        for (Product product: produits) {
            System.out.println(product.toString() + "\n");
        }
    }

    public void printProductsByCate(){
        System.out.println("Liste des produits par catégorie :\n");
        for (Map.Entry<String, Product> entry: listeCate.entrySet()) {
            System.out.println("-" + entry.getKey() + "\n");
            System.out.println("    " + entry.getValue().toString() + "\n");
        }
    }


    //fonction qui va gérer le client pendant son shopping
    public void shopping(){
        boolean shopping = true;
        Checkout checkout = Checkout.getCheckout();
        initCheckoutV1(checkout);
        Customer customer = new Customer();
        String reponse = "0";
        Scanner sc = new Scanner(System.in);
        System.out.println("\nBienvenue cher client !\n");
        while(shopping){

            //TODO gerer la connexion du client

            System.out.println("Voulez-vous afficher la liste entière ou la liste par categories ?\n");
            System.out.println("0 pour quitter le magazin (vos achats seront alors perdu),1 pour la liste entière, 2 pour la liste par categories,  3 pour passer à la caisse\n");
            System.out.println("4 pour afficher l'etat actuel de votre panier\n");
            System.out.println("Pour ajouter un ou plusieurs produits a votre panier entrer le nom du produit");
            reponse = sc.next();
            switch(reponse){
                case "0" :
                    shopping = false;
                    break;
                case "1" :
                    afficheProducts();
                    break;
                case "2" :
                    printProductsByCate();
                    break;
                case "3" :
                    System.out.println("Prix de base : " + customer.rawPrice() + " euros.\n" + "Prix a payer : " + checkout.getPrice(customer.getCart()) + " euros");
                    shopping = false;
                    break;
                case "4" :
                    System.out.println("Panier :\n\n" + customer.cartToString()+"\n\n");
                    break;
                default:
                    Product res = existingProduct(reponse);
                    if(res != null){
                        System.out.println("Combien en voulez-vous ? (entre 1 et 9)\n");
                        reponse = sc.next();
                        if(reponse.charAt(0) <= '9' && reponse.charAt(0) >='1' && reponse.length() == 1) {
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
