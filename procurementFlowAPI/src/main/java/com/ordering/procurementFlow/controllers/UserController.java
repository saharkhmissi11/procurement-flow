package com.ordering.procurementFlow.controllers;
import com.ordering.procurementFlow.DTO.UserDTO;
import com.ordering.procurementFlow.DTO.AuthenticationRequest;
import com.ordering.procurementFlow.DTO.AuthenticationResponse;
import com.ordering.procurementFlow.services.AuthenticationService;
import com.ordering.procurementFlow.DTO.VerificationRequest;
import com.ordering.procurementFlow.services.EmailService;
import com.ordering.procurementFlow.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final EmailService emailService;
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getUsers() throws AccessDeniedException {
        return ResponseEntity.ok(userService.findAllUsers());
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerEmployee(@RequestBody UserDTO request) {
        var response=authenticationService.register(request);
        if(request.isTfaEnbled()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public String get(){
        return "hello auth!!!!";
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request,response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestBody VerificationRequest verificationRequest) {
        // Your method implementation here
        return ResponseEntity.ok(authenticationService.verifyCode(verificationRequest));
    }

}
