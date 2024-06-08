package org.mikedegeofroy.dal.mappers;

import org.mikedegeofroy.dal.entities.Role;
import org.mikedegeofroy.dal.entities.User;
import org.mikedegeofroy.dtos.RoleDto;
import org.mikedegeofroy.dtos.UserDto;

import java.util.stream.Collectors;

public class UserMapper {
    public static RoleDto roleDtoFromEntity(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setRole(role.getRole());
        return dto;
    }

    public static UserDto userDtoFromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles().stream().map(UserMapper::roleDtoFromEntity).collect(Collectors.toList()));
        return dto;
    }
}
