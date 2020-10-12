package edu.wctc.wholesale.service;

import edu.wctc.wholesale.entity.Customer;
import edu.wctc.wholesale.entity.Product;
import edu.wctc.wholesale.exeption.ResourceNotFoundException;
import edu.wctc.wholesale.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepo pRepo;
    public Product getProductById(int id) throws ResourceNotFoundException {
        return pRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", id));
    }
}
