package org.mikedegeofroy.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class AuthDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private boolean success;
    private String token;
    private String refresh;
    private String errorMessage;

    private AuthDto(boolean success, String token, String refresh, String errorMessage) {
        this.success = success;
        this.token = token;
        this.refresh = refresh;
        this.errorMessage = errorMessage;
    }

    public static AuthDto success(String token, String refresh) {
        return new AuthDto(true, token, refresh, null);
    }

    public static AuthDto failure(String errorMessage) {
        return new AuthDto(false, null, null, errorMessage);
    }
}
