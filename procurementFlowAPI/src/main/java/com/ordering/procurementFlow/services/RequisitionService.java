package com.ordering.procurementFlow.services;

import ch.qos.logback.core.spi.ErrorCodes;
import com.ordering.procurementFlow.DTO.ProductDto;
import com.ordering.procurementFlow.DTO.RequisitionDto;
import com.ordering.procurementFlow.Models.Product;
import com.ordering.procurementFlow.Models.Requisition;
import com.ordering.procurementFlow.Models.RequisitionStatus;
import com.ordering.procurementFlow.Models.User;
import com.ordering.procurementFlow.repositories.RequisitionRepo;
import com.ordering.procurementFlow.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequisitionService {

    private final ModelMapper modelMapper ;
    private final RequisitionRepo requisitionRepo ;
    private final UserRepository userRepository ;

    public Optional<RequisitionDto> findRequisitionById(long RequisitionId ){
        if (RequisitionId ==0 ){log.error("RequisitionId is null");}
        Optional<Requisition> requisition= requisitionRepo.findById(RequisitionId);
        return Optional.ofNullable(requisition.map(u -> modelMapper.map(u, RequisitionDto.class)).orElseThrow(() -> new EntityNotFoundException("Requisition" + RequisitionId + "not found")));
    }
    /*public List<RequisitionDto> findAllRequisition(){
        List<Requisition> requisitions= requisitionRepo.findAll() ;
        return requisitions.stream().map(p->modelMapper.map(p,RequisitionDto.class)).collect(Collectors.toList()) ;
    }*/
    public List<RequisitionDto> findAllRequisitions() {
        List<Requisition> requisitions = requisitionRepo.findAll();
        return requisitions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private RequisitionDto convertToDto(Requisition requisition) {
        RequisitionDto requisitionDto = modelMapper.map(requisition, RequisitionDto.class);
        if (requisition.getUser() != null) {
            requisitionDto.setId_user(requisition.getUser().getId());
        }

        return requisitionDto;
    }
    public RequisitionDto addRequisition (RequisitionDto requisitionDto){
        Requisition requisition=modelMapper.map(requisitionDto,Requisition.class);
        requisition.setRequisitionDate(LocalDateTime.now());
        User user = userRepository.findById(requisitionDto.getId_user())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + requisitionDto.getId_user() + " not found"));
        requisition.setUser(user);
        Requisition savedRequisition= requisitionRepo.save(requisition);
        log.debug(savedRequisition.getRequisitionStatus().name());
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
        return status == RequisitionStatus.Approved;
    }
    public List<RequisitionDto> getRequisitionsByUserId(Long userId) {
        List<Requisition> requisitions = requisitionRepo.findAllByUser_Id(userId);
        return requisitions.stream().map(requisition -> modelMapper.map(requisition, RequisitionDto.class)).collect(Collectors.toList());
    }

}
