package edu.badpals.ligas.repository;

import edu.badpals.ligas.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}
