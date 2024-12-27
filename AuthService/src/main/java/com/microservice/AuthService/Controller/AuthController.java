package com.microservice.AuthService.Controller;


import com.microservice.AuthService.dto.RequestDTO.LoginRequestDTO;
import com.microservice.AuthService.dto.ResponseDTO.ApiResponse;
import com.microservice.AuthService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

  @PostMapping("/login")
  public ApiResponse<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
    return authService.loginUser(loginRequestDTO);
  }

}


