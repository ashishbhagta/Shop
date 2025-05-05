package com.DCMetal.Shop.controller;


import com.DCMetal.Shop.model.User;
import com.DCMetal.Shop.payload.AddressDTO;
import com.DCMetal.Shop.repositories.AddressRepository;
import com.DCMetal.Shop.service.AddressService;
import com.DCMetal.Shop.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController
{
    @Autowired
    AuthUtil authUtil;

    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO)
    {
        User user = authUtil.loggedInUser();
        AddressDTO savedAddressDTO = addressService.createAddress(addressDTO,user);
        return new ResponseEntity<>(savedAddressDTO, HttpStatus.CREATED);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses()
    {
        List<AddressDTO> addressList=addressService.getAllAdresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/addresses/{addressesId}")
    public ResponseEntity<AddressDTO> getAddressByID(@PathVariable Long addressesId)
    {
        AddressDTO address=addressService.getAddressById(addressesId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/users/address")
    public ResponseEntity<List<AddressDTO>> getUserAddresses()
    {
        User user=authUtil.loggedInUser();
        List<AddressDTO> address=addressService.getAddressByUser(user);
        return new ResponseEntity<List<AddressDTO>>(address, HttpStatus.OK);
    }

    @PutMapping("/addresses/{addressesId}")
    public ResponseEntity<AddressDTO> updateAddressByID(@PathVariable Long addressesId,@RequestBody AddressDTO addressDTO)
    {
        AddressDTO address=addressService.updateAddressById(addressesId,addressDTO);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{addressesId}")
    public ResponseEntity<String> deleteAddressByID(@PathVariable Long addressesId)
    {
        String status=addressService.deleteAddressById(addressesId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
