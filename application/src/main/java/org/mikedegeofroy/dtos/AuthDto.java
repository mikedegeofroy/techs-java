package org.mikedegeofroy.dtos;

import lombok.Data;

@Data
public class AuthDto {
    String token;
    String refresh;
}
