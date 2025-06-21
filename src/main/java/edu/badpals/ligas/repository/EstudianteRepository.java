package edu.badpals.ligas.repository;

import edu.badpals.ligas.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository <Estudiante , Integer> {
}
