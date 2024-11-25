package ie.atu.week8.projectexercise;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Laptop", "Expensive", 1500);
    }

    @Test
    void testSaveProductForApplyDiscount()
    {
        Product product = new Product(1L, "Laptop", "Expensive", 1500);
        when(productRepository.save(product)).thenReturn(product);
        Product expectedProduct = productService.saveProduct(product);
        assertEquals(1350, expectedProduct.getPrice());
    }

    @Test
    void testValidateProductFailure_NegativePrice() {
        product.setPrice(-1500);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> productService.saveProduct(product));
        assertEquals("Price cannot be negative", iae.getMessage());
    }

    @Test
    void testValidateProduct_EmptyName() {
        product.setName("");
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> productService.saveProduct(product));
        assertEquals("Product name cannot be empty", iae.getMessage());
    }

    @Test
    void testValidateProduct_NullName() {
        product.setName(null);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> productService.saveProduct(product));
        assertEquals("Product name cannot be empty", iae.getMessage());
    }

    @Test
    void testValidateProduct_NullNameAndNegativePrice() {
        product.setName(null);
        product.setPrice(-1000);
        assertThrows(IllegalArgumentException.class, () -> productService.saveProduct(product));
    }
}
