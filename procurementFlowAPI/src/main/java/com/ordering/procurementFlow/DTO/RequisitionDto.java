package com.ordering.procurementFlow.DTO;

import com.ordering.procurementFlow.Models.RequisitionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RequisitionDto {
    private Long id;
    private Date requestedDate ;
    private Date requisitionDate ;
    private String amount ;
    private RequisitionStatus requisitionStatus ;
    private String BuyingLegalEntityId  ;
}
