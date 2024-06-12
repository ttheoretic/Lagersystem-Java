package de.mcc;

import lombok.Data;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Data
@Log
public class Inventory {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        log.info("Product added: " + product);
    }

    public void updateQuantity(String productId, double quantity) {
        boolean productFound = false;
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                product.setQuantity(product.getQuantity() + quantity);
                log.info("Updated quantity for product " + productId + ": " + product.getQuantity());
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            log.warning("Product not found: " + productId);
        }
    }

    public List<Product> searchProducts(String searchString) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(searchString) || product.getCategory().name().contains(searchString)) {
                searchResults.add(product);
            }
        }
        displayProducts(searchResults);
        return searchResults;
    }

    public void displayAllProducts() {
        displayProducts(products);
    }

    private void displayProducts(List<Product> products) {
        String format = "| %-5s | %-20s | %-12s | %-10s | %-10s | %-13s | %-22s | %-10s |%n";
        log.info(String.format(format, "ID", "Name", "Category", "Price", "Weight", "Storage Cost", "Special Storage Required", "Quantity"));
        log.info(String.format(format, "-----", "--------------------", "------------", "----------", "----------", "-------------", "----------------------", "----------"));

        for (Product product : products) {
            log.info(String.format(format,
                    product.getId(),
                    product.getName(),
                    product.getCategory().name(),
                    product.getPrice(),
                    product.getWeight(),
                    product.getStorageCost(),
                    product.isRequiresSpecialStorage() ? "Yes" : "No",
                    product.getQuantity()));
        }
    }

    public double calculateMonthlyStorageCost() {
        double totalCost = 0.0;
        for (Product product : products) {
            double productCost = product.getStorageCost();
            if (product.isRequiresSpecialStorage()) {
                productCost *= 1.5;
            }
            totalCost += productCost * product.getQuantity();
        }
        return totalCost;
    }
}
