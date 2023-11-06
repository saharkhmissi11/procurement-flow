package com.ordering.procurementFlow.DTO;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationRequest {
    private String email;
    private String password;
}
