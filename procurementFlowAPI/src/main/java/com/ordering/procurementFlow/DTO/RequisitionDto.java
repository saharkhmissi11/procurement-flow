package com.ordering.procurementFlow.DTO;

import com.ordering.procurementFlow.Models.RequisitionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class RequisitionDto {
    private Long id;
    private Date requestedDate ;
    private LocalDateTime requisitionDate ;
    private double amount;
    private RequisitionStatus requisitionStatus ;
    private String buyingLegalEntityId ;
    private Long id_user;
}
