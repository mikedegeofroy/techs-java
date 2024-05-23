package org.mikedegeofroy.contracts;

import org.mikedegeofroy.dtos.CatDto;
import org.mikedegeofroy.dtos.OwnerDto;
import org.mikedegeofroy.errors.NotFoundException;

import java.util.List;

public interface OwnerService {

    List<CatDto> getCatsByOwnerId(Integer id) throws NotFoundException;

    void postOwner(OwnerDto owner_dto);

    List<OwnerDto> getOwners();

    OwnerDto getOwnerById(Integer id) throws NotFoundException;

    void addCatToOwner(Integer id, CatDto cat_dto) throws NotFoundException;

    void assignCatToOwner(Integer owner_id, Integer cat_id) throws NotFoundException;

    void deleteOwnerById(Integer id);
}
