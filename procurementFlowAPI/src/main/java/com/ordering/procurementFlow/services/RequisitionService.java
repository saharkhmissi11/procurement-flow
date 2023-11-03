package com.ordering.procurementFlow.services;

import ch.qos.logback.core.spi.ErrorCodes;
import com.ordering.procurementFlow.DTO.RequisitionDto;
import com.ordering.procurementFlow.Models.Requisition;
import com.ordering.procurementFlow.Models.RequisitionStatus;
import com.ordering.procurementFlow.repositories.RequisitionRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequisitionService {

    private final ModelMapper modelMapper ;
    private final RequisitionRepo requisitionRepo ;

    public Optional<RequisitionDto> findRequisitionById(long RequisitionId ){
        if (RequisitionId ==0 ){log.error("RequisitionId is null");}
        Optional<Requisition> requisition= requisitionRepo.findById(RequisitionId);
        return Optional.ofNullable(requisition.map(u -> modelMapper.map(u, RequisitionDto.class)).orElseThrow(() -> new EntityNotFoundException("Requisition" + RequisitionId + "not found")));
    }
    public List<RequisitionDto> findAllRequisition(){
        List<Requisition> requisitions= requisitionRepo.findAll() ;
        return requisitions.stream().map(p->modelMapper.map(p,RequisitionDto.class)).collect(Collectors.toList()) ;
    }
    public RequisitionDto addRequisition (RequisitionDto requisitionDto){
        Requisition requisition=modelMapper.map(requisitionDto,Requisition.class);
        Requisition savedRequisition= requisitionRepo.save(requisition);
        return modelMapper.map(savedRequisition,RequisitionDto.class);
    }

    public RequisitionDto UpdatedRequisition(RequisitionDto requisitionDto){

        Requisition requisition=modelMapper.map(requisitionDto,Requisition.class);
        Requisition savedRequisition= requisitionRepo.save(requisition);
        return modelMapper.map(savedRequisition,RequisitionDto.class);
    }


    public RequisitionDto UpdatedRequisitionStatus(RequisitionDto requisitionDto , RequisitionStatus requisitionStatus){
        requisitionDto.setRequisitionStatus(requisitionStatus);
        Requisition requisition=modelMapper.map(requisitionDto,Requisition.class);
        requisitionRepo.save(requisition);
        return modelMapper.map(requisition,RequisitionDto.class);
    }

    public void DeleteRequisitionById(long requisitionId) {
        if (requisitionId == 0) {
            log.error("requisitionId is null");
        }
       requisitionRepo.deleteById(requisitionId);
    }

    public boolean isRequisitionApproved(Long requisitionId) {
        Optional<Requisition> requisition=requisitionRepo.findById(requisitionId);
        RequisitionStatus status = requisition.get().getRequisitionStatus();
        return status == RequisitionStatus.APPROVED;
    }





}
