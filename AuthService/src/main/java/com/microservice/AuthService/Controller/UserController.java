package com.microservice.AuthService.Controller;


import com.microservice.AuthService.dto.RequestDTO.RoleRequestDTO;
import com.microservice.AuthService.dto.RequestDTO.UserRequestDTO;
import com.microservice.AuthService.dto.ResponseDTO.ApiResponse;
import com.microservice.AuthService.entity.Role;
import com.microservice.AuthService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authUser")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ApiResponse<?> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
           return userService.saveUser(userRequestDTO);
    }

    @PostMapping("/addRole")
    public ApiResponse<?> addRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        return userService.addRole(roleRequestDTO);
    }
}

