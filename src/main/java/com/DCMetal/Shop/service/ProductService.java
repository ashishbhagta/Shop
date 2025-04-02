package com.DCMetal.Shop.service;

import com.DCMetal.Shop.payload.ProductDTO;
import com.DCMetal.Shop.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService
{
    ProductResponse getAllProducts();

    ProductDTO addProduct(Long categoryId, ProductDTO product);

    ProductResponse searchByCateory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, ProductDTO product);

    ProductDTO deleteProduct(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
