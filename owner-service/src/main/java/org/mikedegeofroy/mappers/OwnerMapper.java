package org.mikedegeofroy.mappers;

import org.mikedegeofroy.dtos.OwnerDto;
import org.mikedegeofroy.dal.entities.Owner;

public class OwnerMapper {
    public static OwnerDto mapToDto(Owner owner) {
        if (owner == null) {
            return null;
        }

        OwnerDto ownerDto = new OwnerDto();

        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setSurname(owner.getSurname());
        ownerDto.setBirthdate(owner.getBithdate());

        return ownerDto;
    }

    public static Owner mapToEntity(OwnerDto ownerDto) {
        Owner owner = new Owner();

        owner.setId(ownerDto.getId());
        owner.setName(ownerDto.getName());
        owner.setSurname(ownerDto.getSurname());
        owner.setBithdate(owner.getBithdate());

        return owner;
    }
}
