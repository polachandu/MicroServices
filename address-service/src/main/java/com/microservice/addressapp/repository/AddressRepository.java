package com.microservice.addressapp.repository;

import com.microservice.addressapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(nativeQuery = true, value="SELECT ea.id,ea.lane1,ea.lane2,ea.state,ea.zip FROM EmployeeMicroService.address ea join EmployeeMicroService.employee ee on ee.id = ea.employeeId where ea.employeeId= :employeeId")
    Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
