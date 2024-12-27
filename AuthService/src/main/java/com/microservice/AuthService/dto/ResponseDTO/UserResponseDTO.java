package com.microservice.AuthService.dto.ResponseDTO;


import com.microservice.AuthService.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String username;
    private String email;
    private List<Role> roles;
}
