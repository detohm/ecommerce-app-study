package dev.attaphong.ecommerce_app_study.controller;

import dev.attaphong.ecommerce_app_study.config.JwtTokenProvider;
import dev.attaphong.ecommerce_app_study.controller.error.ApiError;
import dev.attaphong.ecommerce_app_study.dto.LoginDTO;
import dev.attaphong.ecommerce_app_study.dto.UserDTO;
import dev.attaphong.ecommerce_app_study.service.HeaderService;
import dev.attaphong.ecommerce_app_study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);
        String jwtToken = jwtTokenProvider.generateToken(authentication.getName());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({BadCredentialsException.class, CredentialNotFoundException.class})
    ResponseEntity<ApiError> handleBadCredentials(Exception e){
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HeaderService.getInstance().getGlobalHeaders(), apiError.getHttpStatus());
    }
}
