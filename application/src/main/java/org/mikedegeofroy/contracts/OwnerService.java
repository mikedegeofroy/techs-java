package org.mikedegeofroy.contracts;

import org.mikedegeofroy.dtos.CatDto;
import org.mikedegeofroy.dtos.OwnerDto;

import java.util.List;

public interface OwnerService {

    void postOwner(OwnerDto owner_dto);

    List<OwnerDto> getOwners();

    OwnerDto getOwnerById(Integer id);

    void addCatToOwner(Integer id, CatDto cat_dto);

    void assignCatToOwner(Integer owner_id, Integer cat_id);

    void deleteOwnerById(Integer id);
}
