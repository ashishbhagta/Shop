package com.DCMetal.Shop.service;

import com.DCMetal.Shop.payload.CartDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartService
{

    CartDTO addProductToCart(Long productId, Integer quantity);

    List<CartDTO> getAllCarts();

    CartDTO getCart(String email, Long cartId);

    CartDTO updateProductQuantityInCart(Long productId, Integer quantity);

    String deleteProductFromCart(Long cartId, Long productId);

    void updateProductInCarts(Long cartId, Long productId);
}
