package org.mikedegeofroy.services;

import org.mikedegeofroy.abstractions.CatRepository;
import org.mikedegeofroy.abstractions.OwnerRepository;
import org.mikedegeofroy.dtos.CatDto;
import org.mikedegeofroy.dtos.OwnerDto;
import org.mikedegeofroy.errors.NotFoundException;
import org.mikedegeofroy.mappers.CatMapper;
import org.mikedegeofroy.mappers.OwnerMapper;
import org.mikedegeofroy.models.Cat;
import org.mikedegeofroy.models.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements org.mikedegeofroy.contracts.OwnerService {

    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @Override
    public List<CatDto> getCatsByOwnerId(Integer id) throws NotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);

        if (owner.isEmpty()) throw new NotFoundException("Owner with id not found");

        return owner
                .map(value -> value.getCats().stream().map(CatMapper::mapToDto)
                        .collect(Collectors.toList()))
                .orElse(null);
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
    public OwnerDto getOwnerById(Integer id) throws NotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) throw new NotFoundException("Owner with id not found");

        return OwnerMapper.mapToDto(owner.get());
    }

    @Override
    public void addCatToOwner(Integer id, CatDto cat_dto) throws NotFoundException {
        Cat cat = catRepository.save(CatMapper.mapToEntity(cat_dto));
        assignCatToOwner(id, cat.getId());
    }

    @Override
    public void assignCatToOwner(Integer owner_id, Integer cat_id) throws NotFoundException {
        Optional<Cat> cat = catRepository.findById(cat_id);
        if (cat.isEmpty()) throw new NotFoundException("No cat with id found");

        Optional<Owner> owner = ownerRepository.findById(owner_id);
        if (owner.isEmpty()) throw new NotFoundException("No owner with id found");

        owner.get().addCat(cat.get());

        ownerRepository.save(owner.get());
        catRepository.save(cat.get());
    }

    @Override
    public void deleteOwnerById(Integer id) {
        ownerRepository.deleteById(id);
    }
}
