package ie.atu.week8.projectexercise;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveProductForApplyDiscount()
    {
        Product product = new Product(1L, "Laptop", "Expensive", 1500);
        Product expectedProduct = productService.saveProduct(product);
        assertEquals(1350, expectedProduct.getPrice());
    }
}
