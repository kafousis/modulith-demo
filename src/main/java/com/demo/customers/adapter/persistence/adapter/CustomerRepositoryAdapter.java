package com.demo.customers.adapter.persistence.adapter;

import java.util.List;
import java.util.Optional;

import com.demo.customers.adapter.persistence.entity.CustomerEntity;
import com.demo.customers.adapter.persistence.mapper.CustomerEntityMapper;
import com.demo.customers.adapter.persistence.repository.CustomerRepository;
import com.demo.customers.application.port.out.CustomerRepositoryPort;
import com.demo.customers.domain.model.Customer;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll().stream()
                .map(customerEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id)
                .map(customerEntityMapper::toDomain);
    }

    @Override
    public boolean existsByVatNumber(String vatNumber) {
        return customerRepository.existsByVatNumber(vatNumber);
    }

    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = customerEntityMapper.toEntity(customer);
        CustomerEntity saved = customerRepository.save(entity);
        return customerEntityMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
