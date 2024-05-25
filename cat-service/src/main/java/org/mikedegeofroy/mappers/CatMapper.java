package org.mikedegeofroy.mappers;

import org.mikedegeofroy.application.dtos.CatDto;
import org.mikedegeofroy.dal.entities.Cat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CatMapper {

    private static CatDto mapToDtoWithoutFriends(Cat cat) {
        if (cat == null) {
            return null;
        }

        CatDto catDto = new CatDto();
        catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setBirthdate(cat.getBirthdate());
        catDto.setBreed(cat.getBreed());
        catDto.setColor(cat.getColor());
        return catDto;
    }

    public static CatDto mapToDto(Cat cat) {
        if (cat == null) {
            return null;
        }

        CatDto catDto = mapToDtoWithoutFriends(cat);
        catDto.setFriends(cat.getFriends().stream()
                .map(CatMapper::mapToDtoWithoutFriends)
                .collect(Collectors.toList()));

        return catDto;
    }

    private static Cat mapToEntityWithoutFriends(CatDto catDto) {
        if (catDto == null) {
            return null;
        }

        Cat cat = new Cat();
        cat.setId(catDto.getId());
        cat.setName(catDto.getName());
        cat.setBirthdate(catDto.getBirthdate());
        cat.setBreed(catDto.getBreed());
        cat.setColor(catDto.getColor());
        return cat;
    }

    public static Cat mapToEntity(CatDto catDto) {
        if (catDto == null) {
            return null;
        }

        Cat cat = mapToEntityWithoutFriends(catDto);

        List<Cat> friends = (catDto.getFriends() != null)
                ? catDto.getFriends().stream()
                .map(CatMapper::mapToEntityWithoutFriends)
                .collect(Collectors.toList())
                : Collections.emptyList();

        cat.setFriends(friends);

        return cat;
    }
}
