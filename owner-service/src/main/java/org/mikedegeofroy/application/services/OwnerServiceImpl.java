package org.mikedegeofroy.application.services;

import org.mikedegeofroy.application.contracts.OwnerService;
import org.mikedegeofroy.dal.abstractions.OwnerRepository;
import org.mikedegeofroy.dal.entities.Owner;
import org.mikedegeofroy.dtos.OwnerDto;
import org.mikedegeofroy.errors.NotFoundException;
import org.mikedegeofroy.mappers.OwnerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
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
    public void deleteOwnerById(Integer id) {
        ownerRepository.deleteById(id);
    }
}
