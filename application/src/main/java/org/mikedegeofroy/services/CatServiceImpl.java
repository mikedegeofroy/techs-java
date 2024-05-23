package org.mikedegeofroy.services;


import lombok.Getter;
import org.mikedegeofroy.abstractions.CatRepository;
import org.mikedegeofroy.abstractions.OwnerRepository;
import org.mikedegeofroy.dtos.CatDto;
import org.mikedegeofroy.errors.NotFoundException;
import org.mikedegeofroy.mappers.CatMapper;
import org.mikedegeofroy.models.Cat;
import org.mikedegeofroy.models.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
public class CatServiceImpl implements org.mikedegeofroy.contracts.CatService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    public CatServiceImpl(CatRepository catRepository, OwnerRepository ownerRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<CatDto> getCats() {
        List<Cat> cats = catRepository.findAll();
        return cats.stream()
                .map(CatMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeCatById(Integer id) {
        catRepository.deleteById(id);
    }

    @Override
    public void addCat(CatDto catDto) {
        Cat cat = CatMapper.mapToEntity(catDto);
        catRepository.save(cat);
    }

    public List<CatDto> getFriendsById(Integer id) throws NotFoundException {
        Optional<Cat> cat = catRepository.findById(id);

        if (cat.isEmpty()) throw new NotFoundException("Cat with not found");

        return cat.get()
                .getFriends()
                .stream()
                .map(CatMapper::mapToDto)
                .collect(Collectors.toList());
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
    public void addFriendship(Integer from, Integer to) throws NotFoundException {
        Optional<Cat> cat1Optional = catRepository.findById(from);
        Optional<Cat> cat2Optional = catRepository.findById(to);

        if (cat1Optional.isPresent() && cat2Optional.isPresent()) {
            Cat cat1 = cat1Optional.get();
            Cat cat2 = cat2Optional.get();
            cat1.addFriend(cat2);
            catRepository.save(cat1);
            catRepository.save(cat2);
        } else {
            throw new NotFoundException("One or both cats not found");
        }
    }

    @Override
    public void removeFriendship(Integer from, Integer to) throws NotFoundException {
        Optional<Cat> cat1Optional = catRepository.findById(from);
        Optional<Cat> cat2Optional = catRepository.findById(to);

        if (cat1Optional.isPresent() && cat2Optional.isPresent()) {
            Cat cat1 = cat1Optional.get();
            Cat cat2 = cat2Optional.get();
            cat1.removeFriend(cat2);
            catRepository.save(cat1);
            catRepository.save(cat2);
        } else {
            throw new NotFoundException("One or both cats not found");
        }
    }
}
