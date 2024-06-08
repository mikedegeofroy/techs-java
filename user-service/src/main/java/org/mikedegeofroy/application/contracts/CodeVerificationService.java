package org.mikedegeofroy.application.contracts;

public interface CodeVerificationService {
    String generateCode(String email);

    String  verifyCode(String code);
}
