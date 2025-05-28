package com.DCMetal.Shop.repositories;

import com.DCMetal.Shop.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>
{

}
