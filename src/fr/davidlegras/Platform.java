package fr.davidlegras;

import java.util.Collection;

public interface Platform {
    Customer customer();

    /**
     * Renvoie la collection des {@code Product} de la plateforme (actuellement chargé).
     *
     * @return la collection des {@code Product} de la plateforme.
     */
    Collection<Product> products();

    /**
     * Renvoie la collection des {@code Offer} de la plateforme (actuellement chargé).
     *
     * @return la collection des {@code Product} de la plateforme.
     */
    Collection<Offer> offers();

    void addProduct(Product product);

    void addOffer(Offer offer);

    /**
     * Permet de se connecter en tant que client sur la plateforme. Renvoie la statut correspondant de la personne en
     * fonction des données de la plateforme.
     *
     * @param login        identifiant
     * @param passwordHash hash du mot de passe.
     * @return le statut du client
     * @throws WrongCredentials si l'identifiant ou le mot de passe est incorrect.
     */
    CustomerState connect(String login, String passwordHash) throws WrongCredentials;

    /**
     * Déconnecte le client et effectue des opérations si nécessaire.
     */
    void disconnect();

    /**
     * Renvoie le serveur relié à l'instance actuel de la plateforme.
     *
     * @return le serveur relié à l'instance actuel de la plateforme.
     */
    Server server();

    /**
     * Interface interne qui represente la relation avec un serveur extérieur pour accèder aux données de la plateforme.
     */
    interface Server {
        String[] queryCustomer(String login);

        String[] connect(String login, String passwordHash) throws WrongCredentials;

        void disconnect();

        void createAccount(String login, String passwordHash, String name, String lastName, Class<? extends CustomerState> stateClass, LoyaltyCard... args);
    }
}