package edu.badpals.ligas.repository;

import edu.badpals.ligas.model.Concesionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcesionarioRepository extends JpaRepository<Concesionario,String> {
}
