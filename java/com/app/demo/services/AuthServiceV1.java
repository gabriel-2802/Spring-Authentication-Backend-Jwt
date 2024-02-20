package com.app.demo.services;

import com.app.demo.dto.AuthDto;
import com.app.demo.dto.LoginDto;
import com.app.demo.dto.RegisterDto;
import com.app.demo.entities.Role;
import com.app.demo.entities.User;
import com.app.demo.repositories.RoleRepository;
import com.app.demo.repositories.UserRepository;
import com.app.demo.security.JwtGenerator;
import com.app.demo.services.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static java.awt.AWTEventMulticaster.add;

@Service
@RequiredArgsConstructor
public class AuthServiceV1 implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final RoleRepository roleRepository;
    @Override
    public ResponseEntity<String> register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        User newUser = new User();
        newUser.setUsername(registerDto.getUsername());
        newUser.setEmail(registerDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role roles = roleRepository.findByAuthority("USER").get();
        newUser.setRoles(new HashSet<Role>() {{
            add(roles);
        }});

        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");

    }

    @Override
    public ResponseEntity<AuthDto> login(LoginDto loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return ResponseEntity.ok(new AuthDto(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new AuthDto("Invalid username or password"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthDto("Error: " + e.getMessage()));
        }
    }
}
