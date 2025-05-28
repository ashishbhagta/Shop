package com.DCMetal.Shop.service;

import com.DCMetal.Shop.exceptions.APIException;
import com.DCMetal.Shop.exceptions.ResourceNotFoundException;
import com.DCMetal.Shop.model.Address;
import com.DCMetal.Shop.model.User;
import com.DCMetal.Shop.DTO.AddressDTO;
import com.DCMetal.Shop.repositories.AddressRepository;
import com.DCMetal.Shop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService
{
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user)
    {
        Address address = modelMapper.map(addressDTO, Address.class);
        List<Address> addressList=user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);
        address.setUser(user);
        Address savedAddress=addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAllAdresses()
    {
        List<Address> addressList=addressRepository.findAll();
        if(addressList.isEmpty())
        {
            throw new APIException("No address found");
        }
        return addressList.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AddressDTO getAddressById(Long addressesId)
    {
        Address address=addressRepository.findById(addressesId).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", addressesId));
        AddressDTO addressDTO=modelMapper.map(address, AddressDTO.class);
        return addressDTO;
    }

    @Override
    public List<AddressDTO> getAddressByUser(User user)
    {
        List<Address> addressList=user.getAddresses();
        return addressList.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddressById(Long addressesId, AddressDTO addressDTO)
    {
        Address addressFromDatabase=addressRepository.findById(addressesId).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", addressesId));
        addressFromDatabase.setCity(addressDTO.getCity());
        addressFromDatabase.setState(addressDTO.getState());
        addressFromDatabase.setCountry(addressDTO.getCountry());
        addressFromDatabase.setStreet(addressDTO.getStreet());
        addressFromDatabase.setBuildingName(addressDTO.getBuildingName());
        addressFromDatabase.setPincode(addressDTO.getPincode());
        Address updatedAddress=addressRepository.save(addressFromDatabase);
        User user=addressFromDatabase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressesId));
        user.getAddresses().add(updatedAddress);
        userRepository.save(user);
        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public String deleteAddressById(Long addressesId)
    {
        Address addressFromDatabase=addressRepository.findById(addressesId).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", addressesId));
        User user=addressFromDatabase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressesId));
        userRepository.save(user);
        addressRepository.delete(addressFromDatabase);
        return "Address successfully deleted with id: "+addressesId;
    }


}
