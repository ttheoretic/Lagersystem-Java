import de.mcc.Category;
import de.mcc.Inventory;
import de.mcc.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        inventory.addProduct(new Product("P001", "Laptop", Category.ELECTRONICS, 1200.00, 2.5, 10.0, false, 50));
        inventory.addProduct(new Product("P002", "Smartphone", Category.ELECTRONICS, 800.00, 0.2, 5.0, false, 200));
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("P003", "Refrigerator", Category.ELECTRONICS, 1500.00, 75.0, 30.0, true, 10);
        inventory.addProduct(product);

        List<Product> products = inventory.getProducts();
        assertEquals(3, products.size());
        assertTrue(products.contains(product));
    }

    @Test
    public void testUpdateQuantity() {
        inventory.updateQuantity("P001", 10);
        Product product = inventory.getProducts().stream().filter(p -> p.getId().equals("P001")).findFirst().orElse(null);
        assertNotNull(product);
        assertEquals(60, product.getQuantity());
    }

    @Test
    public void testUpdateQuantityProductNotFound() {
        inventory.updateQuantity("P999", 10);
        Product product = inventory.getProducts().stream().filter(p -> p.getId().equals("P999")).findFirst().orElse(null);
        assertNull(product);
    }

    @Test
    public void testSearchProducts() {
        List<Product> results = inventory.searchProducts("Laptop");
        assertEquals(1, results.size());
        assertEquals("Laptop", results.get(0).getName());

        results = inventory.searchProducts("ELECTRONICS");
        assertEquals(2, results.size());
    }

    @Test
    public void testCalculateMonthlyStorageCost() {
        double cost = inventory.calculateMonthlyStorageCost();
        assertEquals(50 * 10.0 + 200 * 5.0, cost);
    }

    @Test
    public void testCalculateMonthlyStorageCostWithSpecialStorage() {
        Product product = new Product("P003", "Refrigerator", Category.ELECTRONICS, 1500.00, 75.0, 30.0, true, 10);
        inventory.addProduct(product);
        double cost = inventory.calculateMonthlyStorageCost();
        assertEquals(50 * 10.0 + 200 * 5.0 + 10 * 45.0, cost);
    }
}

