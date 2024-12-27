package com.microservice.AuthService.service;

import com.microservice.AuthService.dto.RequestDTO.LoginRequestDTO;
import com.microservice.AuthService.dto.ResponseDTO.ApiResponse;

public interface AuthService {

    ApiResponse<?> loginUser(LoginRequestDTO loginRequestDTO);
}
