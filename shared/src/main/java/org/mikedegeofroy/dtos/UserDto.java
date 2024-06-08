package org.mikedegeofroy.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Integer id;
    private String email;
    private List<RoleDto> roles;
}
