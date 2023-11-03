package com.ordering.procurementFlow.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "Product")
@Entity

public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "price")
    private double price ;
    @ManyToOne
    @JoinColumn(name="idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name="providerId")
    private Provider provider ;

    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category ;
}
