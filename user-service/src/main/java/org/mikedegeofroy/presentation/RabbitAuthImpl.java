package org.mikedegeofroy.presentation;

import org.mikedegeofroy.application.contracts.AuthService;
import org.mikedegeofroy.application.services.CustomUserDetailsService;
import org.mikedegeofroy.dtos.AuthDto;
import org.mikedegeofroy.dtos.UserDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RabbitAuthImpl {
    private final RabbitTemplate customRabbitTemplate;
    private final AuthService authService;
    private final CustomUserDetailsService userDetailsService;

    public RabbitAuthImpl(@Qualifier("customRabbitTemplate") RabbitTemplate customRabbitTemplate, AuthService authService, CustomUserDetailsService userDetailsService) {
        this.customRabbitTemplate = customRabbitTemplate;
        this.authService = authService;
        this.userDetailsService = userDetailsService;
    }

    @RabbitListener(queues = "loginQueue")
    public void login(String email) {
        authService.login(email);
    }

    @RabbitListener(queues = "queryUserQueue")
    public UserDto queryUserByUsername(String username) {
        try {
            return userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }

    @RabbitListener(queues = "verifyQueue")
    public AuthDto verify(String code) {
        return authService.verify(code);
    }
}
