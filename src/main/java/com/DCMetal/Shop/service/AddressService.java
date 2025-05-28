package com.DCMetal.Shop.service;

import com.DCMetal.Shop.model.User;
import com.DCMetal.Shop.DTO.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);


    List<AddressDTO> getAllAdresses();

    AddressDTO getAddressById(Long addressesId);

    List<AddressDTO> getAddressByUser(User user);

    AddressDTO updateAddressById(Long addressesId,AddressDTO addressDTO);

    String deleteAddressById(Long addressesId);
}
