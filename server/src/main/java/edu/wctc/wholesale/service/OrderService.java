package edu.wctc.wholesale.service;

import edu.wctc.wholesale.entity.Order;
import edu.wctc.wholesale.exeption.ResourceNotFoundException;
import edu.wctc.wholesale.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository oRepo;
    public List<Order> getAllOrders(){
        List<Order> list=new ArrayList<>();
        oRepo.findAll().forEach(list::add);
        return list;
    }
    public Order getOrderById(int id) throws ResourceNotFoundException {
        return oRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", id));
    }
}
