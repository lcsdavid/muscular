package fr.davidlegras.customer;

public class FidelityCard {
    private static int POINTS_NEEDED_FOR_DISCOUNT = 10;
    private static int FLAT_DISCOUNT_VALUE = 20;
    private int fidelityPoints;

    public FidelityCard() {
        this(0);
    }

    public FidelityCard(int points) {
        super();
        fidelityPoints = points;
    }

    public boolean isUsable() {
        return POINTS_NEEDED_FOR_DISCOUNT <= fidelityPoints;
    }

    public int discount() throws NotEnoughFidelityPointsException {
        if (!isUsable())
            throw new NotEnoughFidelityPointsException("Il n'y pas assez de points de fidelité sur votre carte...");
        fidelityPoints = -POINTS_NEEDED_FOR_DISCOUNT;
        return FLAT_DISCOUNT_VALUE;
    }
}
