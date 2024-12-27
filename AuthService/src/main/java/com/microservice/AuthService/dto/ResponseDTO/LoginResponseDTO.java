package com.microservice.AuthService.dto.ResponseDTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {

    String accessToken;
    String refreshToken;
}
