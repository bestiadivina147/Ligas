package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Curso;
import edu.badpals.ligas.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    public Curso add(Curso curso) {
        return cursoRepository.save(curso);
    }
    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }
    public Curso obtenerPorId(int id) throws RuntimeException {
        return cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }
    public void borrar(int id) throws RuntimeException {
        Curso curso = this.obtenerPorId(id); //Si no la encuentra lanza una excepción
        if (curso.getEstudiantes().size() != 0) {
            throw new RuntimeException("No se puede borrar un Curso con libros");
        }
        cursoRepository.delete(curso);
    }
}
