package main.states;

import main.PaymentType;
import main.entity.VendingMachine;
import main.exceptions.PaymentFailedException;
import main.exceptions.SoldOutException;

public class HasMoneyState implements VendingMachineState{
    private VendingMachine machine;

    public HasMoneyState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void selectProduct(int shelfId) throws SoldOutException {
        System.out.println("Product is not already selected, please insert coins and make payment");
    }

    @Override
    public void insertCoin(double amount) {
        machine.addToBalance(amount);
        System.out.println("Inserted coin: " + amount + " | Current balance: " + machine.getCurrentBalance());
    }

    @Override
    public void makePayment(double amount, PaymentType type) throws PaymentFailedException {
        boolean success = type.processPayment(amount);
        if (success && machine.getCurrentBalance() >= machine.getSelectedProduct().getPrice()){
            System.out.println("Payment Successful");
            machine.setState(machine.getDispensingState());
            machine.dispenseProduct();
        } else{
            throw new PaymentFailedException("Payment failed or insufficient balance");
        }
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Awaiting complete payment to dispense product");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Transaction cancelled. Refunding: " + machine.getCurrentBalance());
        machine.refund();
        machine.setState(machine.getNoMoneyState());
    }
}
