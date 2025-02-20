package main;

import main.entity.Product;
import main.entity.ProductShelf;
import main.entity.VendingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Product coke = new Product("P1", "Coke", 30.00);
        Product pepsi = new Product("P2", "Pepsi", 25.75);

        ProductShelf shelf1 = new ProductShelf();
        shelf1.setShelfId("1");
        shelf1.addProduct(coke, 5);

        ProductShelf shelf2 = new ProductShelf();
        shelf2.setShelfId("2");
        shelf2.addProduct(pepsi, 5);

        List<ProductShelf> shelves = new ArrayList<>();
        shelves.add(shelf1);
        shelves.add(shelf2);

        VendingMachine machine = new VendingMachine(shelves);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit){
            System.out.println("\n--- Vending Machine Menu ---");
            System.out.println("1. Display Products");
            System.out.println("2. Select Product (Enter Shelf Id)");
            System.out.println("3. Insert Coin");
            System.out.println("4. Make Payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option");

            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("\n Available Products:");
                    for (int i = 0; i < shelves.size(); i++){
                        ProductShelf shelf = shelves.get(i);
                        System.out.println("Shelf " + i + ": "
                        + shelf.getProduct().getName()
                        + " | Price: " + shelf.getProduct().getPrice()
                        + " | Quantity: " + shelf.getProductCount());
                    }
                    break;
                case 2:
                    System.out.println("Enter shelf id: ");
                    int shelfId = scanner.nextInt();
                    machine.selectProduct(shelfId);
                    break;
                case 3:
                    System.out.println("Insert coin amount: ");
                    double amount = scanner.nextDouble();
                    System.out.println("Coin of $" + amount + " inserted (this is simulated).");
                    break;
                case 4:
                    System.out.println("Select Payment Method: ");
                    System.out.println("1. Card");
                    System.out.println("2. Digital");
                    int paymentChoice = scanner.nextInt();
                    PaymentType paymentType;
                    if(paymentChoice == 1) {
                        paymentType = new CardPayment();
                    }
                    else {
                        paymentType = new DigitalPayment();
                    }
                    System.out.println("Enter payment amount");
                    double paymentAmount = scanner.nextDouble();
                    machine.makePayment(paymentAmount, paymentType);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                }
            }
        scanner.close();
        System.out.println("Existing Vending Machine Application.");
        }

}
