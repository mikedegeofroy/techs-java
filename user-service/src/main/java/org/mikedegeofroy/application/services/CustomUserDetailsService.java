package org.mikedegeofroy.application.services;

import org.mikedegeofroy.dal.abstractions.UserRepository;
import org.mikedegeofroy.dal.mappers.UserMapper;
import org.mikedegeofroy.dtos.UserDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return UserMapper.userDtoFromEntity(user);
    }
}