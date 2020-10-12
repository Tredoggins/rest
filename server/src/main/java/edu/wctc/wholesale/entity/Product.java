package edu.wctc.wholesale.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @Column(name = "product_id")
    private int id;
    @Column
    private String name;
    @Column
    private double cost;
}
