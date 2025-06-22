package edu.badpals.ligas.repository;

import edu.badpals.ligas.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {
}
