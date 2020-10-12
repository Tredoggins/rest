package edu.wctc.wholesale.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Order {
    private int id;
    private int customerId;
    private String customerName;
    private LocalDateTime shipped;
    private LocalDateTime date;
    private String poNumber;
    private int productId;
    private String productName;
    private String terms;
    private double total;
}
