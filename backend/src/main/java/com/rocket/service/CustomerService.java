package com.rocket.service;

import com.rocket.entity.Customer;
import com.rocket.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    // Add a new customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    // Update a customer
    public Customer updateCustomer(Integer customerId, Customer customerDetails) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (customerDetails.getFirstName() != null) {
                customer.setFirstName(customerDetails.getFirstName());
            }
            if (customerDetails.getLastName() != null) {
                customer.setLastName(customerDetails.getLastName());
            }
            if (customerDetails.getEmail() != null) {
                customer.setEmail(customerDetails.getEmail());
            }
            if (customerDetails.getPhoneNo() != null) {
                customer.setPhoneNo(customerDetails.getPhoneNo());
            }
            if (customerDetails.getOrganizationName() != null) {
                customer.setOrganizationName(customerDetails.getOrganizationName());
            }
            return customerRepository.save(customer);
        }
        return null;
    }
    
    // Delete a customer
    public boolean deleteCustomer(Integer customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }
    
    // Get customer by ID
    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }
    
    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    // Get customer by email
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    
    // Get customer by phone
    public Optional<Customer> getCustomerByPhone(String phoneNo) {
        return customerRepository.findByPhoneNo(phoneNo);
    }
}
