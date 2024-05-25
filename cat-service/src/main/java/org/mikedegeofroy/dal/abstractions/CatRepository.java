package org.mikedegeofroy.dal.abstractions;

import org.mikedegeofroy.dal.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
}
