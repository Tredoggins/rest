package edu.wctc.wholesale.repo;

import edu.wctc.wholesale.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer,Integer> {
}
