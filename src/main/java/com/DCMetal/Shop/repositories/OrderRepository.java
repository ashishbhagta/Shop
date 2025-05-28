package com.DCMetal.Shop.repositories;

import com.DCMetal.Shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>
{

}
