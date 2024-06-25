package gift.product;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void 모든_상품_조회() {
        // given
        productRepository.createProduct(new Product(0L, "productA", 1000, "imageUrlA"));
        productRepository.createProduct(new Product(0L, "productB", 2000, "imageUrlB"));

        // when
        List<Product> allProducts = productRepository.findAllProducts();

        // then
        assertThat(allProducts.size()).isEqualTo(2L);
    }
    
    @Test
    void 상품_정보_수정() {
        // given
        productRepository.createProduct(new Product(1L, "productA", 1000, "imageUrlA"));

        // when
        Product requestBody = new Product(0L, "productC", 0, null);
        Product updatedProduct = productRepository.updateProduct(1L, requestBody);

        // then
        assertThat(requestBody.getName()).isEqualTo(updatedProduct.getName());
    }

    @Test
    void 상품_삭제() {
        // given
        productRepository.createProduct(new Product(1L, "productA", 1000, "imageUrlA"));

        // when
        productRepository.deleteProduct(1L);

        // then
        assertThat(productRepository.findAllProducts().size()).isEqualTo(0);
    }
}