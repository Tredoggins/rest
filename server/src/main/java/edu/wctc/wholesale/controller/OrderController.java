package edu.wctc.wholesale.controller;

import edu.wctc.wholesale.entity.Customer;
import edu.wctc.wholesale.entity.Order;
import edu.wctc.wholesale.entity.Product;
import edu.wctc.wholesale.service.CustomerService;
import edu.wctc.wholesale.service.OrderService;
import edu.wctc.wholesale.service.ProductService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Destination;
import javax.xml.transform.Source;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private CustomerService cService;
    @Autowired
    private ProductService pService;
    @Autowired
    private OrderService oService;
    @Autowired
    private ModelMapper mMapper;
    private edu.wctc.wholesale.dto.Order convertToDto(edu.wctc.wholesale.entity.Order eOrder){
        TypeMap<Order, edu.wctc.wholesale.dto.Order> tMap= mMapper.typeMap(Order.class, edu.wctc.wholesale.dto.Order.class);
        tMap.addMapping(Order::getId, edu.wctc.wholesale.dto.Order::setId);
        tMap.addMappings(mapper -> mapper.map(Order::getPurchaseDate, edu.wctc.wholesale.dto.Order::setDate));
        tMap.addMapping(Order::getShippedDate, edu.wctc.wholesale.dto.Order::setShipped);
        tMap.addMapping(Order::getPurchaseOrderNumber, edu.wctc.wholesale.dto.Order::setPoNumber);
        tMap.addMapping(Order::getTerms, edu.wctc.wholesale.dto.Order::setTerms);
        tMap.addMappings(mapper -> mapper.map(src -> src.getCustomer().getId(), edu.wctc.wholesale.dto.Order::setCustomerId));
        tMap.addMappings(mapper -> mapper.map(src -> src.getCustomer().getName(), edu.wctc.wholesale.dto.Order::setCustomerName));
        tMap.addMappings(mapper -> mapper.map(src -> src.getProduct().getId(), edu.wctc.wholesale.dto.Order::setProductId));
        tMap.addMappings(mapper -> mapper.map(src -> src.getProduct().getName(), edu.wctc.wholesale.dto.Order::setProductName));
        tMap.addMappings(mapper -> mapper.map(src -> src.getProduct().getCost(), edu.wctc.wholesale.dto.Order::setTotal));
        return tMap.map(eOrder);
    }
    private edu.wctc.wholesale.entity.Order convertToEntity(edu.wctc.wholesale.dto.Order dOrder) throws Exception{
        edu.wctc.wholesale.entity.Order order=mMapper.map(dOrder, edu.wctc.wholesale.entity.Order.class);
        order.setPurchaseDate(dOrder.getDate());
        order.setShippedDate(dOrder.getShipped());
        order.setPurchaseOrderNumber(dOrder.getPoNumber());
        order.setTerms(dOrder.getTerms());
        Customer customer=cService.getCustomerById(dOrder.getCustomerId());
        Product product=pService.getProductById(dOrder.getProductId());
        order.setCustomer(customer);
        order.setProduct(product);
        return order;
    }
    @GetMapping("/")
    public List<edu.wctc.wholesale.dto.Order> getAllOrders(){
        return oService.getAllOrders().stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }
}
