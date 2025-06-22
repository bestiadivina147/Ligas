package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Departamento;
import edu.badpals.ligas.model.Empleado;
import edu.badpals.ligas.repository.DepartamentoRepository;
import edu.badpals.ligas.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {
    @Autowired
    DepartamentoRepository departamentoRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    public Departamento add(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    public Departamento obtenerPorId(Long id) throws RuntimeException {
        return departamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Departamento no encontrada"));
    }

    public Departamento editar(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public void borrar(Long id) throws RuntimeException {
        Departamento departamento = this.obtenerPorId(id);
        List <Empleado> empleados = empleadoRepository.findByDepartamento(departamento);
        System.out.println("Empleados asociados: " + empleados.size());
        if (!empleados.isEmpty())
            throw new RuntimeException("No se puede borrar un departamento con empleados");
        departamentoRepository.delete(departamento);
    }

}
