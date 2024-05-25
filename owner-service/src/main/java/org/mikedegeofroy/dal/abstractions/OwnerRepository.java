package org.mikedegeofroy.dal.abstractions;

import org.mikedegeofroy.dal.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
