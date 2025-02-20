package main.states;

import main.PaymentType;
import main.exceptions.PaymentFailedException;
import main.exceptions.SoldOutException;

public interface VendingMachineState {

    void selectProduct(int shelfId) throws SoldOutException;
    void insertCoin(double amount);
    void makePayment(double amount, PaymentType type) throws PaymentFailedException;
    void dispenseProduct();
    void cancelTransaction();


}
