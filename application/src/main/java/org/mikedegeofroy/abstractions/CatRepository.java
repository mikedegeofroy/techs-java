package org.mikedegeofroy.abstractions;

import org.mikedegeofroy.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
}
