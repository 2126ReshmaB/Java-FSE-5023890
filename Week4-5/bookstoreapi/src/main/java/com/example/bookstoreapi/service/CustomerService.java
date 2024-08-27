package com.example.bookstoreapi.service;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.entity.Customer;
import com.example.bookstoreapi.mapper.CustomerMapper;
import com.example.bookstoreapi.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toDTO(customer);
    }

    public CustomerDTO createCustomer(@Valid CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        return customerMapper.toDTO(customerRepository.save(customer));
    }

    public CustomerDTO updateCustomer(Long id, @Valid CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return customerMapper.toDTO(customerRepository.save(customer));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
