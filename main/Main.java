package main;

import main.entity.Product;
import main.entity.ProductShelf;
import main.entity.VendingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Product coke = new Product("P1", "Coke", 1.50);
        Product pepsi = new Product("P2", "Pepsi", 1.75);

        ProductShelf shelf1 = new ProductShelf();
        shelf1.setShelfId("0");
        shelf1.addProduct(coke, 5);

        ProductShelf shelf2 = new ProductShelf();
        shelf2.setShelfId("1");
        shelf2.addProduct(pepsi, 3);

        List<ProductShelf> shelves = new ArrayList<>();
        shelves.add(shelf1);
        shelves.add(shelf2);

        VendingMachine machine = new VendingMachine(shelves);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Vending Machine Menu ---");
            System.out.println("1. Display Products");
            System.out.println("2. Select Product (Enter Shelf ID)");
            System.out.println("3. Insert Coin");
            System.out.println("4. Make Payment");
            System.out.println("5. Cancel Transaction");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Products:");
                    for (int i = 0; i < shelves.size(); i++) {
                        ProductShelf shelf = shelves.get(i);
                        System.out.println("Shelf " + i + ": "
                                + shelf.getProduct().getName()
                                + " | Price: $" + shelf.getProduct().getPrice()
                                + " | Quantity: " + shelf.getProductCount());
                    }
                    break;
                case 2:
                    System.out.print("Enter Shelf ID: ");
                    int shelfId = scanner.nextInt();
                    machine.selectProduct(shelfId);
                    break;
                case 3:
                    System.out.print("Insert coin amount: ");
                    double coin = scanner.nextDouble();
                    machine.insertCoin(coin);
                    break;
                case 4:
                    System.out.println("Select Payment Method:");
                    System.out.println("1. Card Payment");
                    System.out.println("2. Digital Payment");
                    int payChoice = scanner.nextInt();
                    PaymentType payment;
                    if (payChoice == 1) {
                        payment = new CardPayment();
                    } else {
                        payment = new DigitalPayment();
                    }
                    System.out.print("Enter payment amount: ");
                    double payAmount = scanner.nextDouble();
                    machine.makePayment(payAmount, payment);
                    break;
                case 5:
                    machine.cancelTransaction();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
        System.out.println("Exiting Vending Machine Application.");
    }
}
