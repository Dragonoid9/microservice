package com.microservice.AuthService.service;

import com.microservice.AuthService.dto.RequestDTO.LoginRequestDTO;
import com.microservice.AuthService.dto.ResponseDTO.ApiResponse;
import com.microservice.AuthService.dto.ResponseDTO.LoginResponseDTO;
import com.microservice.AuthService.entity.AuthToken;
import com.microservice.AuthService.entity.Role;
import com.microservice.AuthService.entity.User;
import com.microservice.AuthService.repository.AuthTokenRepository;
import com.microservice.AuthService.utility.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthTokenRepository authTokenRepository;



    @Override
    public ApiResponse<?> loginUser(LoginRequestDTO loginRequestDTO) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            String accessToken = jwtService.generateToken(loginRequestDTO.getUsername(), roles);
            String refreshToken = jwtService.generateRefreshToken(loginRequestDTO.getUsername());
            log.info("Access Token: {} Refresh Token: {}", accessToken, refreshToken);
            AuthToken authToken = AuthToken.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .issuedDate(LocalDateTime.now())
                    .used(false)
                    .authUser((User)userDetails)
                    .build();
            log.info("Something after the builder"+authToken.toString());
            authTokenRepository.save(authToken);
            log.info("After the repository saved of the authToken.");
            LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return ResponseUtil.getSuccessResponse(loginResponseDTO, "Login Successful");
        }catch (DataAccessException e) {
            return ResponseUtil.getValidationErrorResponse("Something went wrong. "+"Please contact support.");
        }catch (AuthenticationException e) {
                return ResponseUtil.getValidationErrorResponse("Bad Credentials");
        }catch (Exception e) {
            return ResponseUtil.getErrorResponse(e, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
