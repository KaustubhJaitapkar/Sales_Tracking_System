package com.rocket.service;

import com.rocket.dto.CustomerDTO;
import java.util.List;

public interface ICustomerService {
    
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    
    CustomerDTO updateCustomer(Integer customerId, CustomerDTO customerDTO);
    
    void deleteCustomer(Integer customerId);
    
    CustomerDTO getCustomerById(Integer customerId);
    
    List<CustomerDTO> getAllCustomers();
    
    CustomerDTO getCustomerByEmail(String email);
    
    CustomerDTO getCustomerByPhoneNo(String phoneNo);
    
    List<CustomerDTO> searchCustomers(String searchTerm);
    
    boolean customerExists(Integer customerId);
}
