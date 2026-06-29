package com.rocket.service.impl;

import com.rocket.dto.CustomerDTO;
import com.rocket.entity.Customer;
import com.rocket.mapper.EntityDTOMapper;
import com.rocket.repository.CustomerRepository;
import com.rocket.service.ICustomerEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerEntityServiceImpl implements ICustomerEntityService {
    
    private final CustomerRepository customerRepository;
    private final EntityDTOMapper mapper;
    
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        log.info("Adding new customer with email: {}", customerDTO.getEmail());
        Customer customer = mapper.toCustomerEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return mapper.toCustomerDTO(savedCustomer);
    }
    
    @Override
    public CustomerDTO updateCustomer(Integer customerId, CustomerDTO customerDetails) {
        log.info("Updating customer with ID: {}", customerId);
        Optional<Customer> optionalCustomer = customerRepository.findByIdActive(customerId);
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
            Customer updated = customerRepository.save(customer);
            return mapper.toCustomerDTO(updated);
        }
        return null;
    }
    
    @Override
    public boolean deleteCustomer(Integer customerId) {
        log.info("Deleting customer with ID: {}", customerId);
        Optional<Customer> optionalCustomer = customerRepository.findByIdActive(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setIsActive(false);
            customerRepository.save(customer);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> getCustomerById(Integer customerId) {
        log.info("Fetching customer with ID: {}", customerId);
        return customerRepository.findByIdActive(customerId)
                .map(mapper::toCustomerDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() {
        log.info("Fetching all customers");
        return customerRepository.findAllActive()
                .stream()
                .map(mapper::toCustomerDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> getCustomerByEmail(String email) {
        log.info("Fetching customer by email: {}", email);
        return customerRepository.findByEmail(email)
                .map(mapper::toCustomerDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> getCustomerByPhone(String phoneNo) {
        log.info("Fetching customer by phone: {}", phoneNo);
        return customerRepository.findByPhoneNo(phoneNo)
                .map(mapper::toCustomerDTO);
    }
}
