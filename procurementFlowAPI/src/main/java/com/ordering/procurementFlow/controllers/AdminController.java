package com.ordering.procurementFlow.controllers;

import com.ordering.procurementFlow.DTO.UserDTO;
import com.ordering.procurementFlow.Models.User;
import com.ordering.procurementFlow.authentication.AuthenticationService;
import com.ordering.procurementFlow.authentication.RegisterRequest;
import com.ordering.procurementFlow.services.UserService;
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
    private final UserService userService;

    @PostMapping("/register-employee")
    public ResponseEntity<?> registerEmployee(
            @RequestBody UserDTO request
    ) {
        var response=authenticationService.register(request);
        if(request.isTfaEnbled()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.accepted().build();
    }
    @GetMapping("get-employees")
    public ResponseEntity<List<User>> registerEmployee() {
        return ResponseEntity.ok(userService.findAllEmployees());
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
