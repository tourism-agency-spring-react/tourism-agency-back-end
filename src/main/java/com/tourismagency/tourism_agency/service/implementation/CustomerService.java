package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.Customer;
import com.tourismagency.tourism_agency.persistense.repository.ICustomerRepository;
import com.tourismagency.tourism_agency.service.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void update(Long id, Customer customer) {
        Customer customerGet = this.getById(id);

        customerGet.setFirstName(customer.getFirstName());
        customerGet.setLastName(customer.getLastName());
        customerGet.setPhoneNumber(customer.getPhoneNumber());
        customerGet.setBirthDate(customer.getBirthDate());
        customerGet.setDirection(customer.getDirection());

        customerRepository.save(customerGet);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
