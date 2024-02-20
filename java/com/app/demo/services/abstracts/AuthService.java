package com.app.demo.services.abstracts;

import com.app.demo.dto.AuthDto;
import com.app.demo.dto.LoginDto;
import com.app.demo.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> register(RegisterDto registerDto);

    ResponseEntity<AuthDto> login(LoginDto loginDto);
}
