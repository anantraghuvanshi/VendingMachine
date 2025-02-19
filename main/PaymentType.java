package main;

public interface PaymentType {
    boolean processPayment(double amount);
}

class CardPayment implements PaymentType {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing card payment of $" + amount);
        // For simulation, assume card payments succeed for amounts up to $1000.
        if (amount <= 1000) {
            System.out.println("Card payment successful.");
            return true;
        } else {
            System.out.println("Card payment failed: amount exceeds limit.");
            return false;
        }
    }
}

class DigitalPayment implements PaymentType {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing digital payment of $" + amount);
        // For simulation, assume digital payments always succeed.
        System.out.println("Digital payment successful.");
        return true;
    }
}
