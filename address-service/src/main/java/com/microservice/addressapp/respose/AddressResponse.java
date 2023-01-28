package com.microservice.addressapp.respose;

import lombok.Data;

@Data
public class AddressResponse {

    private int id;
    private String lane1;
    private String lane2;
    private int zip;
    private String state;
}
