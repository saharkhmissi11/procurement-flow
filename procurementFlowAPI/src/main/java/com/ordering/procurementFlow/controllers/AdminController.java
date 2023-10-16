package com.ordering.procurementFlow.controllers;

import com.ordering.procurementFlow.authentication.AuthenticationService;
import com.ordering.procurementFlow.authentication.RegisterRequest;
import com.ordering.procurementFlow.services.EmployeeService;
import com.ordering.procurementFlow.Models.Employee;
import com.ordering.procurementFlow.authentication.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private final AuthenticationService authenticationService;
    private final EmployeeService employeeService;

    @PostMapping("/register-employee")
    public ResponseEntity<AuthenticationResponse> registerEmployee(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @GetMapping("get-employees")
    public ResponseEntity<List<Employee>> registerEmployee() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }


    @GetMapping
    public String get() {
        return "GET:: admin controller";
    }
    @PostMapping
    public String post() {
        return "POST:: admin controller";
    }
    @PutMapping
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping
    public String delete() {
        return "DELETE:: admin controller";
    }
}
