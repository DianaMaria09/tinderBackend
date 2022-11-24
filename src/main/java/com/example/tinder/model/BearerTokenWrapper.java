package com.example.tinder.model;

import com.example.tinder.controller.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BearerTokenWrapper {
    @Autowired
    JwtUtils jwtUtils;

    public String getUsername(String token){
        if(jwtUtils.validateJwtToken(token))
            return jwtUtils.getUserNameFromJwtToken(token);
        return null;
    }
}
