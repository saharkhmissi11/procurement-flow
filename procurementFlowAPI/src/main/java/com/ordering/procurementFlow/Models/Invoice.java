package com.ordering.procurementFlow.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "Invoice")
@Entity
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "reference")
    private String reference ;

    @NonNull
    @Column(name = "price")
    private double price ;

    @NonNull
    @Column(name = "TVA")
    private double TVA ;

    @NonNull
    @Column(name = "invoiceDate")
    private Date invoiceDate ;

    @ManyToOne
    @JoinColumn(name="purchaseManagerId")
    private PurchaseManager purchaseManager;
    @OneToOne
    private PurchaseOrder purchaseOrder;
}
