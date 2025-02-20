package main.entity;

import main.PaymentType;
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
        return shelf.getProduct();
    }

    public Product selectProduct(int shelfId){

        if(shelfId < 0 || shelfId > productShelfList.size()){
            throw new IllegalArgumentException("Invalid shelf id");
        }

        ProductShelf productShelf = productShelfList.get(shelfId);

        if(productShelf.getProductCount() <= 0){
            System.out.println("Product is sold out");
            return null;
        }

        selectedProduct = productShelf.getProduct();
        System.out.println("Selected product: " + selectedProduct.getName() +
                "| Price: " + selectedProduct.getPrice());
        return selectedProduct;
    }

    public void makePayment(double amount, PaymentType type){
        boolean paymentSuccess  = type.processPayment(amount);
        if (paymentSuccess){
            System.out.println("Payment of $" + amount + " successful using " +
                    type.getClass().getSimpleName());
            updateInventoryForSelectedProduct();
            dispenseProduct();

            selectedProduct = null;
        }
        else{
            System.out.println("Payment of $" + amount + " failed using " +
                    type.getClass().getSimpleName());
        }
    }

    private void updateInventoryForSelectedProduct(){
        for (ProductShelf shelf : productShelfList){
            if(shelf.getProduct().equals(selectedProduct)) {
                shelf.reduceCount(1);
                break;
            }
        }
    }

    public void dispenseProduct(){
        System.out.println("Dispensing product: " + selectedProduct.getName());
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
}
