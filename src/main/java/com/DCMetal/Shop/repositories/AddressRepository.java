package com.DCMetal.Shop.repositories;

import com.DCMetal.Shop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>
{
    @Query("SELECT a from Address a where a.user.userId=?1")
    List<Address> getAddressByUser(Long userId);
}
