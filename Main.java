package de.mcc;

import lombok.extern.java.Log;

import java.util.Scanner;

@Log
public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        addSampleProducts(inventory);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            log.warning("Choose an option: \n1. Add product\n2. Update quantity\n3. Search products\n4. Display all products\n5. Calculate monthly storage cost\n6. Exit\nEnter your choice(number)");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    log.warning("Enter product details (id, name, category, price, weight, storage cost, requires special storage (true/false), quantity):");
                    String id = scanner.next();
                    String name = scanner.next();
                    String categoryString = scanner.next();
                    Category category;
                    try {
                        category = Category.valueOf(categoryString.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        log.info("Invalid category. Setting category to OTHER.");
                        category = Category.OTHER;
                    }
                    double price = scanner.nextDouble();
                    double weight = scanner.nextDouble();
                    double storageCost = scanner.nextDouble();
                    boolean requiresSpecialStorage = scanner.nextBoolean();
                    double quantity = scanner.nextDouble();
                    Product product = new Product(id, name, category, price, weight, storageCost, requiresSpecialStorage, quantity);
                    inventory.addProduct(product);
                    break;
                case 2:
                    log.warning("Enter product ID and quantity to update:");
                    String productId = scanner.next();
                    double updateQuantity = scanner.nextDouble();
                    inventory.updateQuantity(productId, updateQuantity);
                    break;
                case 3:
                    log.warning("Enter search string:");
                    String searchString = scanner.next();
                    inventory.searchProducts(searchString).forEach(product1 -> log.info(product1.toString()));
                    break;
                case 4:
                    inventory.displayAllProducts();
                    break;
                case 5:
                    log.warning("Total monthly storage cost: " + inventory.calculateMonthlyStorageCost());
                    break;
                case 6:
                    log.warning("Exiting...");
                    return;
                default:
                    log.info("Invalid choice. Please try again.");
            }
        }
    }

    public static void addSampleProducts(Inventory inventory) {
        inventory.addProduct(new Product("P001", "Laptop", Category.ELECTRONICS, 1200.00, 2.5, 10.0, false, 50));
        inventory.addProduct(new Product("P002", "Smartphone", Category.ELECTRONICS, 800.00, 0.2, 5.0, false, 200));
        inventory.addProduct(new Product("P003", "Refrigerator", Category.ELECTRONICS, 1500.00, 75.0, 30.0, true, 10));
        inventory.addProduct(new Product("P004", "Apple", Category.FOOD, 2.00, 0.1, 0.5, true, 500));
        inventory.addProduct(new Product("P005", "T-Shirt", Category.CLOTHING, 20.00, 0.3, 2.0, false, 300));
        inventory.addProduct(new Product("P006", "Jeans", Category.CLOTHING, 50.00, 0.5, 3.0, false, 150));
        inventory.addProduct(new Product("P007", "Hammer", Category.TOOLS, 15.00, 1.5, 1.0, false, 100));
        inventory.addProduct(new Product("P008", "Nail", Category.TOOLS, 80.00, 3.0, 2.5, false, 50));
        inventory.addProduct(new Product("P009", "Steel", Category.OTHER, 10.00, 25.0, 10.0, false, 40));
        inventory.addProduct(new Product("P010", "Cement", Category.OTHER, 9.00, 30.0, 10.0, true, 80));
    }
}
