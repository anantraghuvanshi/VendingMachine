package main.states;

import main.PaymentType;
import main.entity.Product;
import main.entity.VendingMachine;
import main.exceptions.PaymentFailedException;
import main.exceptions.SoldOutException;

public class NoMoneyState implements VendingMachineState {

    private VendingMachine machine;

    public NoMoneyState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void selectProduct(int shelfId) throws SoldOutException {
        Product product = machine.retrieveProduct(shelfId);
        if (product == null){
            throw new SoldOutException("Product is sold out or invalid shelf id");
        }
        machine.setSelectedProduct(product);
        System.out.println("Product Selected: " + product.getName()
                + " | Price: " + product.getPrice());
        machine.setState(machine.getHasMoneyState());
    }

    @Override
    public void insertCoin(double amount) {
        System.out.println("Please select a product first");
    }

    @Override
    public void makePayment(double amount, PaymentType type) throws PaymentFailedException {
        System.out.println("Product is not selected, cannot process payment");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product is not selected, cannot dispense");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("No transaction to cancel");
    }
}
