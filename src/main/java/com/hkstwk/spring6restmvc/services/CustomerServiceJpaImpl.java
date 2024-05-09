package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.mappers.CustomerMapper;
import com.hkstwk.spring6restmvc.model.CustomerDTO;
import com.hkstwk.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJpaImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDTO(customerRepository
                .findById(id)
                .orElse(null))
        );
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .toList();
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return customerMapper.customerToCustomerDTO(customerRepository.save(customerMapper.customerDTOToCustomer(customer)));
    }

    @Override
    public Optional<CustomerDTO> updateById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer -> {
            foundCustomer.setCustomerName(customer.getCustomerName());
            atomicReference.set(Optional.of(customerMapper.customerToCustomerDTO(foundCustomer)));
            log.debug("Uodated customer with id {}, called in {}", customerId, this.getClass().getSimpleName());
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CustomerDTO> patchCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer -> {
            if (StringUtils.hasText(customer.getCustomerName())) {
                foundCustomer.setCustomerName(customer.getCustomerName());
            }

            log.debug("Patched customer with id {}, called in {}", customerId, this.getClass().getSimpleName());
            atomicReference.set(Optional.of(customerMapper.customerToCustomerDTO(customerRepository.save(foundCustomer))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }
}
