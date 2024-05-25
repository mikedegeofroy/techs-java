package org.mikedegeofroy.application.services;


import lombok.Getter;
import org.mikedegeofroy.application.contracts.CatService;
import org.mikedegeofroy.dal.abstractions.CatRepository;
import org.mikedegeofroy.application.dtos.CatDto;
import org.mikedegeofroy.errors.NotFoundException;
import org.mikedegeofroy.mappers.CatMapper;
import org.mikedegeofroy.dal.entities.Cat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;

    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
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
