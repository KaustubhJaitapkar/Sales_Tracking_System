package com.rocket.service;

import com.rocket.dto.CustomerDTO;
import java.util.List;
import java.util.Optional;

public interface ICustomerEntityService {
    
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    
    CustomerDTO updateCustomer(Integer customerId, CustomerDTO customerDetails);
    
    boolean deleteCustomer(Integer customerId);
    
    Optional<CustomerDTO> getCustomerById(Integer customerId);
    
    List<CustomerDTO> getAllCustomers();
    
    Optional<CustomerDTO> getCustomerByEmail(String email);
    
    Optional<CustomerDTO> getCustomerByPhone(String phoneNo);
}
