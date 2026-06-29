package com.rocket.mapper;

import com.rocket.dto.*;
import com.rocket.entity.*;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class EntityDTOMapper {
    
    // UserAccount Mappings
    public UserAccountDTO toUserAccountDTO(UserAccount userAccount) {
        if (userAccount == null) {
            return null;
        }
        return UserAccountDTO.builder()
                .userId(userAccount.getUserId())
                .employeeId(userAccount.getEmployee() != null ? userAccount.getEmployee().getId() : null)
                .username(userAccount.getUsername())
                .accountStatus(userAccount.getAccountStatus())
                .createdAt(userAccount.getCreatedAt())
                .build();
    }
    
    public UserAccount toUserAccountEntity(UserAccountDTO dto, Employee employee) {
        if (dto == null) {
            return null;
        }
        return UserAccount.builder()
                .username(dto.getUsername())
                .passwordHash(dto.getPassword())
                .accountStatus(dto.getAccountStatus() != null ? dto.getAccountStatus() : "ACTIVE")
                .employee(employee)
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                .build();
    }
    
    // Customer Mappings
    public CustomerDTO toCustomerDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerDTO.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNo(customer.getPhoneNo())
                .organizationName(customer.getOrganizationName())
                .build();
    }
    
    public Customer toCustomerEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setCustomerId(dto.getCustomerId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNo(dto.getPhoneNo());
        customer.setOrganizationName(dto.getOrganizationName());
        customer.setCreatedAt(LocalDateTime.now());
        return customer;
    }
    
    // Lead Mappings
    public LeadDTO toLeadDTO(Lead lead) {
        if (lead == null) {
            return null;
        }
        return LeadDTO.builder()
                .leadId(lead.getLeadId())
                .customerId(lead.getCustomer() != null ? lead.getCustomer().getCustomerId() : null)
                .sourceId(lead.getSource() != null ? lead.getSource().getSourceId() : null)
                .productId(lead.getProduct() != null ? lead.getProduct().getProductId() : null)
                .createdBy(lead.getCreatedBy() != null ? lead.getCreatedBy().getId() : null)
                .statusId(lead.getStatus() != null ? lead.getStatus().getStatusId() : null)
                .description(lead.getDescription())
                .priority(lead.getPriority())
                .assignedTo(lead.getAssignedTo() != null ? lead.getAssignedTo().getId() : null)
                .value(lead.getValue())
                .createdAt(lead.getCreatedAt())
                .updatedAt(lead.getUpdatedAt())
                .build();
    }
    
    public Lead toLeadEntity(LeadDTO dto) {
        if (dto == null) {
            return null;
        }
        Lead lead = new Lead();
        // Note: Do NOT set leadId - it's auto-generated
        lead.setDescription(dto.getDescription());
        lead.setPriority(dto.getPriority() != null ? dto.getPriority() : LeadPriority.Medium);
        lead.setValue(dto.getValue() != null ? dto.getValue() : 0);
        lead.setCreatedAt(LocalDateTime.now());
        lead.setUpdatedAt(LocalDateTime.now());
        return lead;
    }
    
    // Employee Mappings
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .title(employee.getTitle())
                .email(employee.getEmail())
                .country(employee.getCountry())
                .city(employee.getCity())
                .locationId(employee.getLocation() != null ? employee.getLocation().getId() : null)
                .build();
    }
    
    public Employee toEmployeeEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setTitle(dto.getTitle());
        employee.setEmail(dto.getEmail());
        employee.setCountry(dto.getCountry());
        employee.setCity(dto.getCity());
        employee.setCreatedAt(LocalDateTime.now());
        return employee;
    }
    
    // Locations Mappings
    public LocationsDTO toLocationsDTO(Locations locations) {
        if (locations == null) {
            return null;
        }
        return LocationsDTO.builder()
                .id(locations.getId())
                .phone(locations.getPhone())
                .street(locations.getStreet())
                .country(locations.getCountry())
                .city(locations.getCity())
                .build();
    }
    
    public Locations toLocationsEntity(LocationsDTO dto) {
        if (dto == null) {
            return null;
        }
        Locations locations = new Locations();
        locations.setId(dto.getId());
        locations.setPhone(dto.getPhone());
        locations.setStreet(dto.getStreet());
        locations.setCountry(dto.getCountry());
        locations.setCity(dto.getCity());
        return locations;
    }
    
    // ProductDetails Mappings
    public ProductDetailsDTO toProductDetailsDTO(ProductDetails productDetails) {
        if (productDetails == null) {
            return null;
        }
        return ProductDetailsDTO.builder()
                .productId(productDetails.getProductId())
                .productName(productDetails.getProductName())
                .productType(productDetails.getProductType())
                .productStatus(productDetails.getProductStatus())
                .description(productDetails.getDescription())
                .build();
    }
    
    public ProductDetails toProductDetailsEntity(ProductDetailsDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(dto.getProductId());
        productDetails.setProductName(dto.getProductName());
        productDetails.setProductType(dto.getProductType());
        productDetails.setProductStatus(dto.getProductStatus());
        productDetails.setDescription(dto.getDescription());
        return productDetails;
    }
    
    // LeadSource Mappings
    public LeadSourceDTO toLeadSourceDTO(LeadSource leadSource) {
        if (leadSource == null) {
            return null;
        }
        return LeadSourceDTO.builder()
                .sourceId(leadSource.getSourceId())
                .sourceName(leadSource.getSourceName())
                .build();
    }
    
    public LeadSource toLeadSourceEntity(LeadSourceDTO dto) {
        if (dto == null) {
            return null;
        }
        LeadSource leadSource = new LeadSource();
        leadSource.setSourceId(dto.getSourceId());
        leadSource.setSourceName(dto.getSourceName());
        return leadSource;
    }
    
    // LeadStatus Mappings
    public LeadStatusDTO toLeadStatusDTO(LeadStatus leadStatus) {
        if (leadStatus == null) {
            return null;
        }
        return LeadStatusDTO.builder()
                .statusId(leadStatus.getStatusId())
                .statusName(leadStatus.getStatusName())
                .build();
    }
    
    public LeadStatus toLeadStatusEntity(LeadStatusDTO dto) {
        if (dto == null) {
            return null;
        }
        LeadStatus leadStatus = new LeadStatus();
        leadStatus.setStatusId(dto.getStatusId());
        leadStatus.setStatusName(dto.getStatusName());
        return leadStatus;
    }
    
    // LeadAssignment Mappings
    public LeadAssignmentDTO toLeadAssignmentDTO(LeadAssignment leadAssignment) {
        if (leadAssignment == null) {
            return null;
        }
        return LeadAssignmentDTO.builder()
                .assignmentId(leadAssignment.getAssignmentId())
                .leadId(leadAssignment.getLead() != null ? leadAssignment.getLead().getLeadId() : null)
                .assignedToId(leadAssignment.getAssignedTo() != null ? leadAssignment.getAssignedTo().getId() : null)
                .assignedById(leadAssignment.getAssignedBy() != null ? leadAssignment.getAssignedBy().getId() : null)
                .remarks(leadAssignment.getRemarks())
                .assignedAt(leadAssignment.getAssignedAt())
                .build();
    }
    
    public LeadAssignment toLeadAssignmentEntity(LeadAssignmentDTO dto) {
        if (dto == null) {
            return null;
        }
        LeadAssignment leadAssignment = new LeadAssignment();
        leadAssignment.setAssignmentId(dto.getAssignmentId());
        leadAssignment.setRemarks(dto.getRemarks());
        leadAssignment.setAssignedAt(dto.getAssignedAt() != null ? dto.getAssignedAt() : LocalDateTime.now());
        return leadAssignment;
    }
}
