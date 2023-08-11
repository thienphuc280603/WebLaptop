package com.poly.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Orders {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Orderid")
   int id;

    @ManyToOne
    @JoinColumn(name = "userid")
    Users user;

    @Column(name = "createdate")
    Date createDate;

    @Column(name = "address")
    String address;

    @OneToMany(mappedBy = "order")
   List<Item> items;
    
    boolean active;
}
