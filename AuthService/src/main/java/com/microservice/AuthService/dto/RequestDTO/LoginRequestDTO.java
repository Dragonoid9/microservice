package com.microservice.AuthService.dto.RequestDTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {

    String username;
    String password;

}
