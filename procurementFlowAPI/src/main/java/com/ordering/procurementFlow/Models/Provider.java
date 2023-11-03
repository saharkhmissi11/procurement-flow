package com.ordering.procurementFlow.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Provider")
@Entity
public class Provider {
    @Id
    @GeneratedValue
    private Long id;

    @Column( name = "CodeQR")
    private  String codeQR;

    @OneToMany(mappedBy = "provider")
    private List<Product> products;
}
