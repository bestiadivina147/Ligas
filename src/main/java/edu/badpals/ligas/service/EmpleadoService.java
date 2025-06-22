package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Departamento;
import edu.badpals.ligas.model.Empleado;
import edu.badpals.ligas.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public void save(Empleado empleado) {
        empleadoRepository.save(empleado);
    }


    public Empleado obtenerPorId(Long id) throws RuntimeException {
        return empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrada"));
    }

    public void borrar(Long id) throws RuntimeException {
        Empleado empleado = this.obtenerPorId(id); //Si no la encuentra lanza una excepción
        empleadoRepository.delete(empleado);
    }

    public Empleado editar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public List<Empleado> findByDep(Departamento departamento) {
        return empleadoRepository.findByDepartamento(departamento);
    }
}
