package com.microservice.addressapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.addressapp.entity.Address;
import com.microservice.addressapp.repository.AddressRepository;
import com.microservice.addressapp.respose.AddressResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AddressRepository addressRepository;

    public AddressResponse findAddressByEmployeeId(int employeeId){
        Address address = addressRepository.findAddressByEmployeeId(employeeId);
        AddressResponse addressResponse = mapper.map(address, AddressResponse.class);
        return addressResponse;
    }
}
