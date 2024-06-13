package org.example.final_server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.AuthRequest;
import org.example.final_server.dto.response.AuthResponse;
import org.example.final_server.entity.user.RoleUser;
import org.example.final_server.entity.user.User;

import org.example.final_server.service.AuthenticationService;
import org.example.final_server.service.JwtService;
import org.example.final_server.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse register(AuthRequest authRequest) {
        User user = new User();
        user.setRole(RoleUser.ROLE_USER);
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        userService.create(user);
        return getAuthResponse(user);
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        return getAuthResponse(userDetails);
    }

    private AuthResponse getAuthResponse(UserDetails userDetails) {
        final String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }
}
