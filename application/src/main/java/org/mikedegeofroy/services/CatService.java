package org.mikedegeofroy.services;


import lombok.Getter;
import org.mikedegeofroy.abstractions.CatRepository;
import org.mikedegeofroy.abstractions.OwnerRepository;
import org.mikedegeofroy.dtos.CatDto;
import org.mikedegeofroy.mappers.CatMapper;
import org.mikedegeofroy.models.Cat;
import org.mikedegeofroy.models.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
public class CatService implements org.mikedegeofroy.contracts.CatService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    public CatService(CatRepository catRepository, OwnerRepository ownerRepository) {
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

    public List<CatDto> getFriendsById(Integer id) {
        return catRepository.findById(id)
                .get()
                .getFriends()
                .stream()
                .map(CatMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CatDto> getCatsByOwnerId(Integer id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        return owner
                .map(value -> value.getCats().stream().map(CatMapper::mapToDto)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public void addFriendship(Integer from, Integer to) {
        Optional<Cat> cat1Optional = catRepository.findById(from);
        Optional<Cat> cat2Optional = catRepository.findById(to);

        if (cat1Optional.isPresent() && cat2Optional.isPresent()) {
            Cat cat1 = cat1Optional.get();
            Cat cat2 = cat2Optional.get();
            cat1.addFriend(cat2);
            catRepository.save(cat1);
            catRepository.save(cat2);
        } else {
            throw new RuntimeException("One or both cats not found");
        }
    }

    @Override
    public void removeFriendship(Integer from, Integer to) {
        Optional<Cat> cat1Optional = catRepository.findById(from);
        Optional<Cat> cat2Optional = catRepository.findById(to);

        if (cat1Optional.isPresent() && cat2Optional.isPresent()) {
            Cat cat1 = cat1Optional.get();
            Cat cat2 = cat2Optional.get();
            cat1.removeFriend(cat2);
            catRepository.save(cat1);
            catRepository.save(cat2);
        } else {
            throw new RuntimeException("One or both cats not found");
        }
    }
}
