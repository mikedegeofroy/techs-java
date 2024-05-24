package org.mikedegeofroy.contracts;

import org.mikedegeofroy.dtos.AuthDto;
import org.mikedegeofroy.errors.NotFoundException;

public interface AuthService {
    void login(String email);
    AuthDto verify(String code) throws NotFoundException;

    AuthDto refresh(String refresh) throws NotFoundException;
}
