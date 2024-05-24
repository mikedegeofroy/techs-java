package org.mikedegeofroy.services;

import org.mikedegeofroy.contracts.CodeVerificationService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CodeVerificationServiceImpl implements CodeVerificationService {
    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();

    public String generateCode(String email) {
        String code = String.valueOf(new Random().nextInt(999999));
        verificationCodes.put(code, email);
        return code;
    }

    @Override
    public String verifyCode(String code) {
        return verificationCodes.get(code);
    }
}
