package com.ordering.procurementFlow.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Requisition")
@Entity
public class Requisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date requestedDate ;
    private Date requisitionDate ;
    private String amount ;
    @Enumerated(EnumType.STRING)
    private RequisitionStatus requisitionStatus ;
    private String BuyingLegalEntityId  ;
    @ManyToOne
    @JoinColumn(name="idUser")
    private User user ;
    @OneToMany(mappedBy = "requisition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RequisitionLine> requisitionLines;
}
