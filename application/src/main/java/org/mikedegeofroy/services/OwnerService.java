package org.mikedegeofroy.services;

import org.mikedegeofroy.abstractions.CatRepository;
import org.mikedegeofroy.abstractions.OwnerRepository;
import org.mikedegeofroy.dtos.CatDto;
import org.mikedegeofroy.dtos.OwnerDto;
import org.mikedegeofroy.mappers.CatMapper;
import org.mikedegeofroy.mappers.OwnerMapper;
import org.mikedegeofroy.models.Cat;
import org.mikedegeofroy.models.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService implements org.mikedegeofroy.contracts.OwnerService {

    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    public OwnerService(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @Override
    public void postOwner(OwnerDto owner_dto) {
        ownerRepository.save(OwnerMapper.mapToEntity(owner_dto));
    }

    @Override
    public List<OwnerDto> getOwners() {
        return ownerRepository.findAll().stream().map(OwnerMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public OwnerDto getOwnerById(Integer id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        return OwnerMapper.mapToDto(owner.get());
    }

    @Override
    public void addCatToOwner(Integer id, CatDto cat_dto) {
        Cat cat = catRepository.save(CatMapper.mapToEntity(cat_dto));
        assignCatToOwner(id, cat.getId());
    }

    @Override
    public void assignCatToOwner(Integer owner_id, Integer cat_id) {
        Optional<Cat> cat = catRepository.findById(cat_id);
        if (cat.isPresent()) {
            Optional<Owner> owner = ownerRepository.findById(owner_id);
            owner.ifPresent(value -> {
                value.addCat(cat.get());
                ownerRepository.save(owner.get());
            });
            catRepository.save(cat.get());
        }
    }

    @Override
    public void deleteOwnerById(Integer id) {
        ownerRepository.deleteById(id);
    }
}
