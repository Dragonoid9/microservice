package com.microservice.AuthService.service;


import com.microservice.AuthService.dto.RequestDTO.RoleRequestDTO;
import com.microservice.AuthService.dto.RequestDTO.UserRequestDTO;
import com.microservice.AuthService.dto.ResponseDTO.ApiResponse;
import com.microservice.AuthService.entity.Role;
import com.microservice.AuthService.entity.User;
import com.microservice.AuthService.exception.ResourceAlreadyExistsException;
import com.microservice.AuthService.exception.ResourceNotFoundException;
import com.microservice.AuthService.repository.RoleRepository;
import com.microservice.AuthService.repository.UserRepository;
import com.microservice.AuthService.utility.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public ApiResponse<?> saveUser(UserRequestDTO userRequestDTO) {

        String username = userRequestDTO.getUsername();
        String email = userRequestDTO.getEmail();

        if (userRepository.existsByUsername(username)) {
            throw new ResourceAlreadyExistsException("Username '" + username + "' is already taken.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new ResourceAlreadyExistsException("Email '" + email + "' is already registered.");
        }

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        List<Role> roles = userRequestDTO.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(()-> new ResourceNotFoundException("Role not found: "+ roleName)))
                .toList();

        User user = User.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .roles(roles)
                .build();
        userRepository.save(user);
        return ResponseUtil.getSuccessResponse("User Saved Successfully");
    }

    @Override
    public ApiResponse<?> addRole(RoleRequestDTO roleRequestDTO) {
        String name = roleRequestDTO.getName();

        if(roleRepository.existsByName(name)){
            throw new ResourceAlreadyExistsException("Role name: '" + name + "' is already registered.");
        }
        Role role =Role.builder()
                .name(name)
                .build();
        Role savedRole = roleRepository.save(role);

        return ResponseUtil.getSuccessResponse("Role Saved Successfully");
    }

}
