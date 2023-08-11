package com.poly.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productid;

    private String productname;
    private double price;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String brand;
    private String promotion;
    private String processor;
    private String RAM;
    private String VGA;
    private String mass;
    private String other;
    private boolean active;
    private String descriptions;

    @ManyToOne
    @JoinColumn(name = "categoriesid")
    private Category categoriesid;
}
