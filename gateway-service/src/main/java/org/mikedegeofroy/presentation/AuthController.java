package org.mikedegeofroy.presentation;

import org.mikedegeofroy.application.contracts.AuthService;
import org.mikedegeofroy.dtos.AuthDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email) {
        authService.login(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<AuthDto> verify(@RequestParam String code) {
        var res = authService.verify(code);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
