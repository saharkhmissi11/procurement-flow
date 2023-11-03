package com.ordering.procurementFlow.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RequisitionLine")
@Entity
public class RequisitionLine {
    @Id
    @GeneratedValue
    private Long id ;
    private double quantity ;
    private double unitPrice ;
    //*** juste le CategoryName (hassitha zeyda puique produit a un categ donc le categ on peut la connaitre depuis le prod)
    private String CategoryName ;
    private double totalAmount = quantity * unitPrice;
    //*** a quoi sert ?? ======> 3la 5ter el user ynajem ya5tar product mch mawjoud fel catalog .. donc ahouka juste ya3ti 3lih description
    @Column(name = "nonCatalogItemDescription")
    private  double nonCatalogItemDescription ;
    @ManyToOne
    @JoinColumn(name = "requisition_id") // assuming this is the column name in your RequisitionLine table
    private Requisition requisition;

}
