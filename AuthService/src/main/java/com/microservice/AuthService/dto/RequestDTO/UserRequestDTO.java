package com.microservice.AuthService.dto.RequestDTO;

import com.microservice.AuthService.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String username;
    private String email;
    private String password;
    private List<String> roles;

}
