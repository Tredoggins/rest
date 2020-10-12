package edu.wctc.wholesale.service;

import edu.wctc.wholesale.entity.Customer;
import edu.wctc.wholesale.exeption.ResourceNotFoundException;
import edu.wctc.wholesale.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo cRepo;
    public Customer getCustomerById(int id) throws ResourceNotFoundException {
        return cRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", id));
    }
}
