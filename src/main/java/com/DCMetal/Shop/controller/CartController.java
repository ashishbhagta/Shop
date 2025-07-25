package com.DCMetal.Shop.controller;

import com.DCMetal.Shop.model.Cart;
import com.DCMetal.Shop.DTO.CartDTO;
import com.DCMetal.Shop.repositories.CartRepository;
import com.DCMetal.Shop.service.CartService;
import com.DCMetal.Shop.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController
{
    @Autowired
    private CartService cartService;
    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long productId, @PathVariable Integer quantity)
    {
        CartDTO cartDTO = cartService.addProductToCart(productId,quantity);
        return new ResponseEntity<CartDTO>(cartDTO,HttpStatus.CREATED);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<CartDTO>> getCarts()
    {
        List<CartDTO> cartDTOS = cartService.getAllCarts();
        return new ResponseEntity<List<CartDTO>>(cartDTOS,HttpStatus.FOUND);
    }

    @GetMapping("/carts/users/cart")
    public ResponseEntity<CartDTO> getCartById()
    {
        String emailId= authUtil.loggedInEmail();
        Cart cart= cartRepository.findCartByEmail(emailId);
        Long cartId= cart.getCartId();
        CartDTO cartDTO=cartService.getCart(emailId,cartId);
        return new ResponseEntity<CartDTO>(cartDTO,HttpStatus.OK);
    }

    @PutMapping("/cart/products/{productId}/quantity/{operation}")
    public ResponseEntity<CartDTO> updateCartProduct(@PathVariable Long productId, @PathVariable String operation)
    {
        CartDTO cartDTO=cartService.updateProductQuantityInCart(productId,operation.equalsIgnoreCase("delete")?-1:1);

        return new ResponseEntity<CartDTO>(cartDTO,HttpStatus.OK);
    }

    @DeleteMapping("/carts/{cartId}/product/{productId}")
    public  ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId,@PathVariable Long productId)
    {
        String status=cartService.deleteProductFromCart(cartId,productId);
        return new ResponseEntity<String>(status,HttpStatus.OK);
    }

}
