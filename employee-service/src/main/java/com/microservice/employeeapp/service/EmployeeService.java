package com.microservice.employeeapp.service;

import com.microservice.addressapp.respose.AddressResponse;
import com.microservice.addressapp.service.AddressService;
import com.microservice.employeeapp.dao.EmployeeRepository;
import com.microservice.employeeapp.entity.Employee;
import com.microservice.employeeapp.response.EmployeeResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
    private RestTemplate restTemplate;

//    @Value("${address.base.url}")
//    private String addressBaseURL;


    public EmployeeService(@Value("${address.base.url}") String addressBaseURL, RestTemplateBuilder builder) {
        this.restTemplate = builder
                            .rootUri(addressBaseURL)
                            .build();
    }

    public EmployeeResponse getEmployeeById(int id){

        //addressResponse data will be set by making a rest api call
//        AddressResponse addressResponse = new AddressResponse();

        Employee employee = employeeRepository.findById(id).get();

        EmployeeResponse employeeResponse = modelMapper.map(employee,EmployeeResponse.class);

        //Instead of matching manually you can use model mapper that was implemented in above line.
//        EmployeeResponse employeeResponse = new EmployeeResponse();
//        employeeResponse.setId(employee.getId());
//        employeeResponse.setName(employee.getName());
//        employeeResponse.setEmail(employee.getName());
//        employeeResponse.setBloodGroup(employee.getBloodGroup());

        AddressResponse addressResponse = restTemplate.getForObject("/address/{id}", AddressResponse.class, id);

        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }
}
