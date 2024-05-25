package org.mikedegeofroy.application.dtos;

import lombok.Data;

@Data
public class AuthDto {
    String token;
    String refresh;
}
