package main.entity;

import main.PaymentType;

import java.util.List;

public class VendingMachine {

    private Product selectedProduct;
    private List<ProductShelf> productShelfList;

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

    void makePayment(double amount, PaymentType type){
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

    private void dispenseProduct(){
        System.out.println("Dispensing product: " + selectedProduct.getName());
    }
}
