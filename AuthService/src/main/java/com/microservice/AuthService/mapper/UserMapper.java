package com.microservice.AuthService.mapper;


import com.microservice.AuthService.dto.RequestDTO.UserRequestDTO;
import com.microservice.AuthService.dto.ResponseDTO.UserResponseDTO;
import com.microservice.AuthService.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
// User toUser(UserRequestDTO userRequestDTO);

 UserResponseDTO toUserResponse(User user);


}
