package edu.badpals.ligas.repository;

import edu.badpals.ligas.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
