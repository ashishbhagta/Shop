package com.DCMetal.Shop.repositories;

import com.DCMetal.Shop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>
{

}
