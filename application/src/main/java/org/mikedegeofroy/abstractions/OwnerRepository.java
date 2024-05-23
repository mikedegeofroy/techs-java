package org.mikedegeofroy.abstractions;

import org.mikedegeofroy.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
