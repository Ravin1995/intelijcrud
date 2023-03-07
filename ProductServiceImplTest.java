package com.ensat.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ensat.entities.Product;
import com.ensat.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Method under test: {@link ProductServiceImpl#listAllProducts()}
     */
    @Test
    void testListAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);
        Iterable<Product> actualListAllProductsResult = productServiceImpl.listAllProducts();
        assertSame(productList, actualListAllProductsResult);
        assertTrue(((Collection<Product>) actualListAllProductsResult).isEmpty());
        verify(productRepository).findAll();
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductById(Integer)}
     */
    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setProductId("42");
        product.setVersion(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findById((Integer) any())).thenReturn(ofResult);
        Product actualProductById = productServiceImpl.getProductById(1);
        assertSame(product, actualProductById);
        assertEquals("42", actualProductById.getPrice().toString());
        verify(productRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductById(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProductById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.ensat.services.ProductServiceImpl.getProductById(ProductServiceImpl.java:25)
        //   See https://diff.blue/R013 to resolve this issue.

        when(productRepository.findById((Integer) any())).thenReturn(Optional.empty());
        productServiceImpl.getProductById(1);
    }

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(Product)}
     */
    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setProductId("42");
        product.setVersion(1);
        when(productRepository.save((Product) any())).thenReturn(product);

        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(42L));
        product1.setProductId("42");
        product1.setVersion(1);
        Product actualSaveProductResult = productServiceImpl.saveProduct(product1);
        assertSame(product, actualSaveProductResult);
        assertEquals("42", actualSaveProductResult.getPrice().toString());
        verify(productRepository).save((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(Integer)}
     */
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById((Integer) any());
        productServiceImpl.deleteProduct(1);
        verify(productRepository).deleteById((Integer) any());
    }
}

