package org.mikedegeofroy.application.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.mikedegeofroy.application.contracts.AuthService;
import org.mikedegeofroy.application.contracts.CodeVerificationService;
import org.mikedegeofroy.dal.abstractions.UserRepository;
import org.mikedegeofroy.dal.entities.Role;
import org.mikedegeofroy.dal.entities.User;
import org.mikedegeofroy.dtos.AuthDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {
    private final CodeVerificationService verificationCodeService;
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public AuthServiceImpl(CodeVerificationService codeVerificationService, UserRepository userRepository) {
        this.verificationCodeService = codeVerificationService;
        this.userRepository = userRepository;
    }

    @Override
    public void login(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(email);

            Role role = new Role();
            role.setRole("Admin");
            newUser.addRole(role);

            userRepository.save(newUser);
        }
        String code = verificationCodeService.generateCode(email);
        System.out.println(code);
    }

    @Override
    public AuthDto verify(String code) {
        String email = verificationCodeService.verifyCode(code);
        Optional<User> user = userRepository.findByEmail(email);

        return user.map(value -> AuthDto.success(generateToken(value), "refresh")).orElseGet(() -> AuthDto.failure("No user found"));
    }

    @Override
    public AuthDto refresh(String refresh) {
        return AuthDto.failure("nope.");
    }

    private String generateToken(User user) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());

        List<String> roles = user.getRoles().stream()
                .map(Role::getRole)
                .toList();

        return Jwts.builder()
                .claims(claims)
                .subject(user.getEmail())
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key)
                .compact();
    }
}
