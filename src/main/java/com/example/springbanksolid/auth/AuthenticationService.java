package com.example.springbanksolid.auth;


import com.example.springbanksolid.DAO.UserRepository;
import com.example.springbanksolid.config.JwtService;
import com.example.springbanksolid.exceptions.NotFoundAccount;
import com.example.springbanksolid.exceptions.NotFoundUser;
import com.example.springbanksolid.model.Role.Role;
import com.example.springbanksolid.model.User.User_table;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.springbanksolid.model.Role.Role.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User_table.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.createNewUser(user.getUsername(),user.getPassword(), String.valueOf(user.getRole()));
//        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            var user  = repository.findUser_tableByUsername(request.getUsername())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public String jwtTokenExtract (HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        String jwt = authHeader.substring(7);
        return jwt;
    }

    public String getClientIdFromRequest(HttpServletRequest request) {
            String token = jwtTokenExtract(request);
            int id = repository.findUser_tableByUsername(jwtService.extractUsername(token)).orElseThrow(()->new NotFoundUser("User not found")).getId();
            String clientId = String.valueOf(id);
            return clientId;
    }
}
