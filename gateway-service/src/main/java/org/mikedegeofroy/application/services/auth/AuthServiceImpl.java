package org.mikedegeofroy.application.services.auth;

import org.mikedegeofroy.application.contracts.AuthService;
import org.mikedegeofroy.dtos.AuthDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final RabbitTemplate customRabbitTemplate;

    public AuthServiceImpl(@Qualifier("customRabbitTemplate") RabbitTemplate customRabbitTemplate) {
        this.customRabbitTemplate = customRabbitTemplate;
    }

    @Override
    public void login(String email) {
        customRabbitTemplate.convertAndSend("authExchange", "auth.login", email);
    }

    @Override
    public AuthDto verify(String code) {
        AuthDto result = (AuthDto) customRabbitTemplate.convertSendAndReceive("authExchange", "auth.verify", code);
        return result;
    }

    @Override
    public AuthDto refresh(String refresh) {
        return null;
    }
}
