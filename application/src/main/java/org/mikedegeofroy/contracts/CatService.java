package org.mikedegeofroy.contracts;

import org.mikedegeofroy.dtos.CatDto;

import java.util.List;

public interface CatService {
    List<CatDto> getCats();

    void removeCatById(Integer id);

    void addCat(CatDto cat);

    void addFriendship(Integer from, Integer to);

    void removeFriendship(Integer from, Integer to);

    List<CatDto> getFriendsById(Integer id);

    List<CatDto> getCatsByOwnerId(Integer id);
}
