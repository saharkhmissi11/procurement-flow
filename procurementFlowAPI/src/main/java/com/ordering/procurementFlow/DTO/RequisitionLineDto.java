package com.ordering.procurementFlow.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequisitionLineDto {
    private Long id;
    private double quantity ;
    private double unitPrice ;
    private double totalAmount = quantity * unitPrice;
    private  String nonCatalogItemDescription ;
    private Long productId;
}
