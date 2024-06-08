package org.mikedegeofroy.application.contracts;

import org.mikedegeofroy.dtos.AuthDto;

public interface AuthService {
    void login(String email);
    AuthDto verify(String code);

    AuthDto refresh(String refresh);
}
