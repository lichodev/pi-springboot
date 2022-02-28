package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.UserPi;
import com.ml.primerainfanciarest.security.jwt.JwtProvider;
import com.ml.primerainfanciarest.security.models.JwtResponse;
import com.ml.primerainfanciarest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService service;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Validated UserPi user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity("bad request", HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername());
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
