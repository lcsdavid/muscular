package fr.davidlegras.customer;

public class LoyaltyCard {
    private static int POINTS_NEEDED_FOR_DISCOUNT = 10;
    private static int FLAT_DISCOUNT_VALUE = 20;
    private int loyaltyPoints;

    public LoyaltyCard() {
        this(0);
    }

    public LoyaltyCard(int points) {
        super();
        loyaltyPoints = points;
    }

    public boolean isUsable() {
        return POINTS_NEEDED_FOR_DISCOUNT <= loyaltyPoints;
    }

    public int discount() throws NotEnoughFidelityPointsException {
        if (!isUsable())
            throw new NotEnoughFidelityPointsException("Il n'y pas assez de points de fidelité sur votre carte...");
        loyaltyPoints = -POINTS_NEEDED_FOR_DISCOUNT;
        return FLAT_DISCOUNT_VALUE;
    }


}
