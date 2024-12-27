package com.microservice.AuthService.service;

import com.microservice.AuthService.dto.RequestDTO.RoleRequestDTO;
import com.microservice.AuthService.dto.RequestDTO.UserRequestDTO;
import com.microservice.AuthService.dto.ResponseDTO.ApiResponse;

public interface UserService {

    ApiResponse<?> saveUser(UserRequestDTO userRequestDTO);

    ApiResponse<?> addRole(RoleRequestDTO roleRequestDTO);
}
