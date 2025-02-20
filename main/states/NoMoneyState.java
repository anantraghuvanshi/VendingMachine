package main.states;

import main.PaymentType;
import main.entity.Product;
import main.entity.VendingMachine;
import main.exceptions.PaymentFailedException;
import main.exceptions.SoldOutException;

public class NoMoneyState implements VendingMachineInterface{

    private VendingMachine machine;

    public NoMoneyState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void selectProduct(int shelfId) throws SoldOutException {

    }

    @Override
    public void insertCoin(double amount) {

    }

    @Override
    public void makePayment(double amount, PaymentType type) throws PaymentFailedException {

    }

    @Override
    public void dispenseProduct() {

    }

    @Override
    public void cancelTransaction() {

    }
}
