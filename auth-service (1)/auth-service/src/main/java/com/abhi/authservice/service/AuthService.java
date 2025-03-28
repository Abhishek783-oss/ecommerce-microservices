package com.abhi.authservice.service;

import com.abhi.authservice.dto.AuthRequest;
import com.abhi.authservice.model.User;
import com.abhi.authservice.repository.UserRepository;
import com.abhi.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String register(AuthRequest authRequest){
        if(userRepository.findByEmail(authRequest.getEmail()).isPresent()) {
            throw new RuntimeException("User Already Exists");
        }
        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
            return jwtUtil.generateToken(user.getEmail());
    }
        public String login(AuthRequest authRequest){
            User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
            if(!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())){
                throw new RuntimeException("Invalid password");
            }
            return jwtUtil.generateToken(user.getEmail());
        }
}
