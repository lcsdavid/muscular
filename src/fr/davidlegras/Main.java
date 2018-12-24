package fr.davidlegras;


import fr.davidlegras.customer.*;
import fr.davidlegras.product.Product;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        laFac laFacDotCom = new laFac();

        Scanner sc = new Scanner(System.in);
        String response = null;

        Customer customer = new Customer();

        System.out.println("Bienvenue cher/chère client.e.s !\n");
        boolean end = false;
        while (!end) {
            /* Affichage des instructions. */
            System.out.println(customer.toString() + ", choisissez parmis les choix suivants:");
            if (!customer.isConnected())
                System.out.println("[SeConnecter]: Se connecter à son profil personnel.");
            else
            System.out.println("[SeDéconnecter]: Se déconnecter de son profil personnel.");
            System.out.println("[Produits]: Afficher la liste des produits.");
            System.out.println("[Panier]: Pour afficher le panier.");
            System.out.println("[Payer]: Pour passer à la caisse.");
            System.out.println("[Quitter]: Quitter le magasin (vos achats seront alors perdu).");
            System.out.println();
            System.out.println("Pour ajouter un ou plusieurs produits à votre panier entrez le nom du produit.");

            /* Réponse du programme. */
            response = sc.next();
            for (int i = 0; i < 10; i++) System.out.println(); /* C'est moche je sais... */
            switch (response) {
                case "SeConnecter":
                    System.out.println("Entrez votre nom d'utilisateur : ");
                    String login = sc.next();
                    System.out.println("Entrez votre mot de passe : ");
                    /* Sécurité */
                    try {
                        String passwordHash = "";
                        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                        byte[] hash = messageDigest.digest(sc.next().getBytes(StandardCharsets.UTF_8));
                        for (byte b : hash) {
                            String hex = Integer.toHexString(0xff & b);
                            if (hex.length() == 1)
                                passwordHash += '0';
                            passwordHash += hex;
                        }
                        System.out.println(passwordHash);
                        /* Connexion */
                        customer.connect(laFacDotCom, login, passwordHash);
                    } catch (AlreadyConnectedException e) {
                        System.out.println("Vous êtes déjà connecté... Déconnectez vous avant de pouvoir vous reconnecter.");
                    } catch (WrongCredentials e) {
                        e.printStackTrace();
                        System.out.println("Mauvais identifiant ou mot de passe...");
                    } catch (NoSuchAlgorithmException ignored) {}
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
                            Collections.sort((ArrayList<Product>) laFacDotCom.products(), new Comparator<Product>() {
                                @Override
                                public int compare(Product o1, Product o2) {
                                    return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
                                }
                            });
                            break;

                        default:
                            Collections.sort((ArrayList<Product>) laFacDotCom.products(),
                                    Comparator.comparing(Product::productTitle));
                            break;
                    }
                    break;
                case "Panier":
                    System.out.println("Panier :");
                    customer.cart().toString();
                    break;
                case "Payer":
                    System.out.println("Récap du panier :");
                    customer.cart().toString();
                    System.out.println("Ce que vous payer : " + customer.price(laFacDotCom));
                    break;
                case "Quitter":
                    end = true;
                    break;
                default:
                    Product toAddProduct = null;
                    for (Product product: laFacDotCom.products()) {
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
