package com.ordering.procurementFlow.services;
import com.ordering.procurementFlow.DTO.RequisitionLineDto;
import com.ordering.procurementFlow.Models.RequisitionLine;
import com.ordering.procurementFlow.repositories.RequisitionLineRepo;
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
public class RequisitionLineService {
    private final ModelMapper modelMapper ;
    private final RequisitionLineRepo requisitionLineRepo;

    public Optional<RequisitionLineDto> findRequisitionLineById(Long requisitionLineId){
        if (requisitionLineId==0){log.error("RequisitionLine is null");}

            Optional<RequisitionLine> requisitionLine=requisitionLineRepo.findById(requisitionLineId);
            return requisitionLine.map(u->modelMapper.map(u,RequisitionLineDto.class));
    }
     public List<RequisitionLineDto> findAllRequisitionLine(){
        List<RequisitionLine> requisitionLines=requisitionLineRepo.findAll();
         return requisitionLines.stream().map(u->modelMapper.map(u,RequisitionLineDto.class)).collect(Collectors.toList());
     }

     public RequisitionLineDto addRequisitionLine (RequisitionLineDto requisitionLineDto){
        RequisitionLine requisitionLine= modelMapper.map(requisitionLineDto,RequisitionLine.class);
         requisitionLineRepo.save(requisitionLine);
         return modelMapper.map(requisitionLine,RequisitionLineDto.class);
     }
     public RequisitionLineDto UpdateRequisitionLine(RequisitionLineDto requisitionLineDto){
         RequisitionLine requisitionLine= modelMapper.map(requisitionLineDto,RequisitionLine.class);
       RequisitionLine savedRequisitionLine = requisitionLineRepo.save(requisitionLine);
         return modelMapper.map(savedRequisitionLine,RequisitionLineDto.class);

     }


    public void DeleteRequisitionLineById(long requisitionLineId) {
        if (requisitionLineId == 0) {
            log.error("requisitionLineId is null");
        }
        requisitionLineRepo.deleteById(requisitionLineId);
    }



}
