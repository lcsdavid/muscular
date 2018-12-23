package fr.davidlegras;


import fr.davidlegras.customer.Customer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String response;
        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.println("Bienvenue cher client !\n");
        while (shopping) {

            //TODO gerer la connexion du client

            System.out.println("Bienvenue cher/chère client.e.s !");
            while (shopping) {
                /* Affichage des instructions. */
                System.out.println("Voulez-vous afficher la liste entière ou la liste par categories ?\n");
                System.out.println(customer.toString() + ", voulez-vous afficher la liste entière ou la liste par categories ?\n");
                System.out.println("[0]: Quitter le magasin (vos achats seront alors perdu).");
                System.out.println("[1]: Liste des produits.");
                System.out.println("[2]: Liste des produits par categorie.");
                System.out.println("[3]: Passer à la caisse.");
                System.out.println("[4]: Afficher l'état actuel de votre panier.");
                System.out.println();
                if (!customer.isSignedIn())
                    System.out.println("[SignIn]: Se connecter son profil personnel.");
                else
                    System.out.println("[SignOut]: Se déconnecter de son profil personnel.");
                System.out.println();
                System.out.println("Pour ajouter un ou plusieurs produits à votre panier entrez le nom du produit.");

                /* Réponse du programme. */
                reponse = sc.next();
                switch (reponse) {
                    response = sc.next();
                    for (int i = 0; i < 50; i++) System.out.println(); /* C'est moche je sais... */
                    switch (response) {
                        case "0":
                            shopping = false;
                            break;
                        @@ -136,23 +181,51 @@ public void shopping() {
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
                                    customer.signIn(login, passwordHash);
                                } catch (AlreadySignedInException e) {
                                    System.out.println("Vous êtes déjà connecté... Déconnectez vous avant de pouvoir vous reconnecter.");
                                } catch (WrongCredentials wrongCredentials) {
                                    System.out.println(wrongCredentials.getMessage());
                                }
                                break;
                            case "SignOut":
                                try {
                                    customer.signOut();
                                } catch (NotSignedInException e) {
                                    System.out.println("Vous ne pouvez pas vous déconnecter, vous n'êtes pas connecter...");
                                }
                                break;
                            default:
                                Product res = existingProduct(reponse);
                                Product res = existingProduct(response);
                                if (res != null) {
                                    System.out.println("Combien en voulez-vous ? (entre 1 et 9)\n");
                                    reponse = sc.next();
                                    if (reponse.charAt(0) <= '9' && reponse.charAt(0) >= '1' && reponse.length() == 1) {
                                        customer.addToCart(res, reponse.charAt(0) - '0');
                                        System.out.println("Combien en voulez-vous ? (entre 1 et 9)");
                                        response = sc.next();
                                        if (response.charAt(0) <= '9' && response.charAt(0) >= '1' && response.length() == 1) {
                                            customer.addToCart(res, response.charAt(0) - '0');
                                            continue;
                                        }
                                    }
                                    System.out.println("\nCette entrée n'est pas valide !!\n");
                                    System.out.println("Cette entrée n'est pas valide !!");
                                    break;
                                }

                                System.out.println();
                        }

                        System.out.println("Nous espérons vous revoir bientôt.\n");
                        System.out.println("Nous espérons vous revoir bientôt.");
                    }

    }
}
