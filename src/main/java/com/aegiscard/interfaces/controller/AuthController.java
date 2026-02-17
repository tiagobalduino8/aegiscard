package com.aegiscard.interfaces.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aegiscard.infrastructure.persistence.User;
import com.aegiscard.infrastructure.persistence.UserRepository;
import com.aegiscard.infrastructure.security.JwtUtil;
import com.aegiscard.interfaces.dto.LoginRequest;
import com.aegiscard.interfaces.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthController(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
		if (userOpt.isPresent() && passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
			String token = jwtUtil.generateToken(request.getUsername());
			return ResponseEntity.ok(new LoginResponse(token));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
