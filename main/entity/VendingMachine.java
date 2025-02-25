package main.entity;

import main.PaymentType;
import main.exceptions.PaymentFailedException;
import main.exceptions.SoldOutException;
import main.states.DispensingState;
import main.states.HasMoneyState;
import main.states.NoMoneyState;
import main.states.VendingMachineState;

import java.util.List;

public class VendingMachine {

    private Product selectedProduct;
    private double currentBalance = 0;
    private List<ProductShelf> productShelfList;

    private VendingMachineState noMoneyState;
    private VendingMachineState hasMoneyState;
    private VendingMachineState dispensingState;
    private VendingMachineState currentState;

    public VendingMachine(List<ProductShelf> productShelfList){
        this.productShelfList = productShelfList;
        noMoneyState = new NoMoneyState(this);
        hasMoneyState = new HasMoneyState(this);
        dispensingState = new DispensingState(this);
        currentState = noMoneyState;
    }

    public void setState(VendingMachineState state){
        this.currentState = state;
    }

    public VendingMachineState getDispensingState() {
        return dispensingState;
    }

    public VendingMachineState getHasMoneyState() {
        return hasMoneyState;
    }

    public VendingMachineState getNoMoneyState() {
        return noMoneyState;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void resetTransaction() {
        this.currentBalance = 0;
        this.selectedProduct = null;
    }

    public Product retrieveProduct(int shelfId){
        if(shelfId < 0 || shelfId > productShelfList.size()){
            return null;
        }
        ProductShelf shelf = productShelfList.get(shelfId);
        if (shelf.getProductCount() <= 0){
            return null;
        }
        return shelf.getProduct();
    }

    public void selectProduct(int shelfId){
        try{
            currentState.selectProduct(shelfId);
        } catch (SoldOutException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertCoin(double amount) {
        currentState.insertCoin(amount);
    }

    public void makePayment(double amount, PaymentType type){
        try{
            currentState.makePayment(amount, type);
        } catch (PaymentFailedException e){
            System.out.print(e.getMessage());
        }
    }

    public void updateInventoryForSelectedProduct(){
        for (ProductShelf shelf : productShelfList){
            if(shelf.getProduct().equals(selectedProduct)) {
                shelf.reduceCount(1);
                break;
            }
        }
    }

    public void dispenseProduct(){
        currentState.dispenseProduct();
    }

    public void addToBalance(double amount) {
        this.currentBalance += amount;
    }

    public double getCurrentBalance(){
        return currentBalance;
    }

    public void refund() {
        System.out.println("Refunding: " + currentBalance);
        resetTransaction();
    }

    public void cancelTransaction(){
        currentState.cancelTransaction();
    }
}
