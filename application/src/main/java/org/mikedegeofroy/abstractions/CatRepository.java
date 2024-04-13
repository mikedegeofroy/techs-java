package org.mikedegeofroy.abstractions;

import org.mikedegeofroy.dtos.Cat;

import java.util.List;

public interface CatRepository {

    List<Cat> GetCats();

    void AddCat();

    void SetCat();
}
