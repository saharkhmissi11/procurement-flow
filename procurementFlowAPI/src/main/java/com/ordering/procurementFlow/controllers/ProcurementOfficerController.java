package com.ordering.procurementFlow.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/procurement")
public class ProcurementOfficerController {
    @GetMapping
    public String get(){
        return "GET:: procurementOfficer controller";
    }
    @PostMapping
    public String post(){
        return "POST:: procurementOfficer controller";
    }
    @PutMapping
    public String put(){
        return "PUT:: procurementOfficer controller";
    }
    @DeleteMapping
    public String delete(){
        return "DELETE:: procurementOfficer controller";
    }
}
