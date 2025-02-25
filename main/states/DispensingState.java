package main.states;

import main.PaymentType;
import main.entity.VendingMachine;
import main.exceptions.PaymentFailedException;
import main.exceptions.SoldOutException;

public class DispensingState implements VendingMachineState{
    private VendingMachine machine;

    public DispensingState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void selectProduct(int shelfId) {
        System.out.println("Currently dispensing a product. Please wait.");
    }

    @Override
    public void insertCoin(double amount) {
        System.out.println("Currently dispensing a product. Cannot insert coins.");
    }

    @Override
    public void makePayment(double amount, PaymentType payment) {
        System.out.println("Already processing the transaction.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Dispensing product: " + machine.getSelectedProduct().getName());
        machine.updateInventoryForSelectedProduct();
        machine.resetTransaction();
        machine.setState(machine.getNoMoneyState());
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Cannot cancel; dispensing in progress.");
    }
}
