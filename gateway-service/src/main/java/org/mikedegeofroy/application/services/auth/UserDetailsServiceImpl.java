package org.mikedegeofroy.application.services.auth;

import org.mikedegeofroy.dtos.UserDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final RabbitTemplate customRabbitTemplate;

    public UserDetailsServiceImpl(@Qualifier("customRabbitTemplate") RabbitTemplate customRabbitTemplate) {
        this.customRabbitTemplate = customRabbitTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = (UserDto) customRabbitTemplate.convertSendAndReceive("authExchange", "auth.queryUser", username);

        return new User(user.getEmail(), "", user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList()));
    }
}
