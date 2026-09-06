package com.springboot.springbootSecurity.controllers;

import com.springboot.springbootSecurity.dto.LoginDto;
import com.springboot.springbootSecurity.dto.SignUpDto;
import com.springboot.springbootSecurity.dto.UserDto;
import com.springboot.springbootSecurity.services.AuthService;
import com.springboot.springbootSecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signup(signUpDto);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        // Implement your login logic here

        String token = authService.login(loginDto);
        return ResponseEntity.ok(token);
    }

}
