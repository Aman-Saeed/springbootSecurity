package com.springboot.springbootSecurity.services;


import com.springboot.springbootSecurity.dto.LoginDto;
import com.springboot.springbootSecurity.dto.SignUpDto;
import com.springboot.springbootSecurity.dto.UserDto;
import com.springboot.springbootSecurity.entities.User;
import com.springboot.springbootSecurity.exceptions.ResourceNotFoundException;
import com.springboot.springbootSecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserDto signup(SignUpDto signUpDto) {

        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
        if (user.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User toCreateUser = modelMapper.map(signUpDto, User.class);
        toCreateUser.setPassword(passwordEncoder.encode(toCreateUser.getPassword()));

        User savedUser = userRepository.save(toCreateUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

}
