package com.example.tinder.controller;

import com.example.tinder.model.entities.User;
import com.example.tinder.service.interfaces.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;


    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        User user = userService.getByLogin(loginRequest.getUsername(), loginRequest.getPassword());
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password incorrect");
        }
        else {
            /*Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User userDetails = (User) authentication.getPrincipal();*/
            LoginResponse loginResponse = new LoginResponse(user.getId(), jwtUtils.generateTokenFromUsername(user.getUsername()));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginResponse);
        }
    }
}
