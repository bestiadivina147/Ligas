package edu.badpals.ligas.repository;

import edu.badpals.ligas.model.Coche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocheRepository extends JpaRepository<Coche , Integer> {
}
