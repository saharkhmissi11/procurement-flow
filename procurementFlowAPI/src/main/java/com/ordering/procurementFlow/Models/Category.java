package com.ordering.procurementFlow.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Category")
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id ;
    @Column(name = "name")
    private String name ;
    @OneToMany(mappedBy="category")
    private List<Product> products;

}
