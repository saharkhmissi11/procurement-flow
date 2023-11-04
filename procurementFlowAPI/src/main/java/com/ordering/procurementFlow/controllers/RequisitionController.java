package com.ordering.procurementFlow.controllers;
import com.ordering.procurementFlow.DTO.RequisitionDto;
import com.ordering.procurementFlow.DTO.RequisitionLineDto;
import com.ordering.procurementFlow.services.RequisitionLineService;
import com.ordering.procurementFlow.services.RequisitionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requisition")
@RequiredArgsConstructor
public class RequisitionController {
    private final RequisitionService requisitionService;
    private final RequisitionLineService requisitionLineService;

    @GetMapping("/all")
    public ResponseEntity<List<RequisitionDto>> getAllRequisitions(){
        List<RequisitionDto> requisitionDto= requisitionService.findAllRequisitions();
        return new ResponseEntity<>(requisitionDto, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RequisitionDto> getRequisitionById( @PathVariable("id")Long requisitionId){
       Optional<RequisitionDto> requisitionDto= requisitionService.findRequisitionById(requisitionId);
        if (requisitionDto.isPresent()) {
            return new ResponseEntity<>(requisitionDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    @PostMapping("/add")
    //User doit être désérialisé (Conversion d'un objet Json ou xml en objet Java )
    public  ResponseEntity<RequisitionDto> addRequisition(@RequestBody RequisitionDto requisitionDto, HttpServletResponse response){
        RequisitionDto newRequisition =requisitionService.addRequisition(requisitionDto)  ;
        return new ResponseEntity<>(newRequisition,HttpStatus.CREATED );
    }

    @PutMapping("/update")
    public ResponseEntity<RequisitionDto> UpdateRequisition(@RequestBody RequisitionDto requisitionDto , HttpServletResponse response) {
        RequisitionDto updateRequisition = requisitionService.UpdatedRequisition(requisitionDto);
        return new ResponseEntity<>(updateRequisition, HttpStatus.OK);
    }
    @PutMapping("/isRequisitionApproved/{id}")
    public ResponseEntity<Boolean> isRequisitionApproved(@PathVariable("id") Long id){
        boolean isRequisitionApproved = requisitionService.isRequisitionApproved(id);
        return new ResponseEntity<Boolean>(isRequisitionApproved, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteRequisition(@PathVariable("id") Long id){
        requisitionService.DeleteRequisitionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    } @GetMapping("/{id}/requisitionLines")
    public ResponseEntity<List<RequisitionLineDto>> getAllRequisitionLinesForRequisition(
            @PathVariable("id") Long requisitionId) {
        List<RequisitionLineDto> requisitionLines = requisitionLineService.getAllRequisitionLinesForRequisition(requisitionId);
        return new ResponseEntity<>(requisitionLines, HttpStatus.OK);
    }
    @PostMapping("/{requisitionId}/addRequisitionLine")
    public ResponseEntity<RequisitionLineDto> addRequisitionLineToRequisition(
            @PathVariable("requisitionId") Long requisitionId,
            @RequestBody RequisitionLineDto requisitionLineDto,
            HttpServletResponse response) {
        RequisitionLineDto newRequisitionLine = requisitionLineService.addRequisitionLineToRequisition(requisitionId, requisitionLineDto);
        return new ResponseEntity<>(newRequisitionLine, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RequisitionDto>> getRequisitionsByUserId(@PathVariable Long userId) {
        List<RequisitionDto> requisitions = requisitionService.getRequisitionsByUserId(userId);
        return ResponseEntity.ok(requisitions);
    }


    }
