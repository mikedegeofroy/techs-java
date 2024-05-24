package org.mikedegeofroy.contracts;

public interface CodeVerificationService {
    String generateCode(String email);

    String  verifyCode(String code);
}
