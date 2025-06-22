package edu.badpals.ligas.repository;

import edu.badpals.ligas.model.Departamento;
import edu.badpals.ligas.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
    List<Empleado> findByDepartamento(Departamento departamento);
}
