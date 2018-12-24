package fr.davidlegras;

public class LoyaltyCard {

    public final static int POINTS_NEEDED_FOR_DISCOUNT = 10;

    public final static int FLAT_DISCOUNT_VALUE = 20;

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

    public double discount() throws NotEnoughFidelityPointsException {
        if (!isUsable())
            throw new NotEnoughFidelityPointsException("Il n'y pas assez de points de fidelité sur votre carte...");
        loyaltyPoints = -POINTS_NEEDED_FOR_DISCOUNT;
        return FLAT_DISCOUNT_VALUE;
    }

    @Override
    public String toString() {
        return "" + loyaltyPoints;
    }
}
