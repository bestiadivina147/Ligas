package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Curso;
import edu.badpals.ligas.model.Estudiante;
import edu.badpals.ligas.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;
    public Estudiante add(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }
    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    public Estudiante obtenerPorId(int id) throws RuntimeException {
        return estudianteRepository.findById(id).orElseThrow(() -> new RuntimeException("estudiante no encontrado"));
    }
    public void borrar(int id) throws RuntimeException {
        Estudiante estudiante = this.obtenerPorId(id); //Si no la encuentra lanza una excepción
        if (estudiante.getCursos().size() != 0) {
            throw new RuntimeException("No se puede borrar un estudiante con cursos");
        }
        estudianteRepository.delete(estudiante);
    }

}
