package com.DCMetal.Shop.repositories;


import com.DCMetal.Shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem, Long>
{

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.product.productId=?2")
    CartItem findCartByProductIdAndCartId(Long cartId, Long productId);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.cartId=?1 AND ci.product.productId=?2")
    void deleteCartItemByProductIdAndCartId(Long cartId, Long productId);
}
