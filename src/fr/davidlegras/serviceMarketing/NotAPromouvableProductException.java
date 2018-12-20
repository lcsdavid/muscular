package fr.davidlegras.serviceMarketing;

public class NotAPromouvableProductException extends ReductionCreationException {
    public NotAPromouvableProductException() {
        super();
    }

    public NotAPromouvableProductException(String message) {
        super(message);
    }
}
