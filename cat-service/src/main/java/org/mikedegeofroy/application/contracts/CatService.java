package org.mikedegeofroy.application.contracts;

import org.mikedegeofroy.application.dtos.CatDto;
import org.mikedegeofroy.errors.NotFoundException;

import java.util.List;

public interface CatService {
    List<CatDto> getCats();

    void removeCatById(Integer id);

    void addCat(CatDto cat);

    void addFriendship(Integer from, Integer to) throws NotFoundException;

    void removeFriendship(Integer from, Integer to) throws NotFoundException;

    List<CatDto> getFriendsById(Integer id) throws NotFoundException;
}
