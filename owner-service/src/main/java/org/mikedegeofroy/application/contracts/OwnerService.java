package org.mikedegeofroy.application.contracts;

import org.mikedegeofroy.application.dtos.OwnerDto;
import org.mikedegeofroy.errors.NotFoundException;

import java.util.List;

public interface OwnerService {
    void postOwner(OwnerDto owner_dto);

    List<OwnerDto> getOwners();

    OwnerDto getOwnerById(Integer id) throws NotFoundException;

    void deleteOwnerById(Integer id);
}
