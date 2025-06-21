package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Autor;
import edu.badpals.ligas.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    AutorRepository autorRepository;

    public Autor add(Autor autor) {
        return autorRepository.save(autor);
    }
    public List<Autor> obtenerTodos() {
        return autorRepository.findAllWithLibros();
    }
    public Autor obtenerPorId(int id) throws RuntimeException {
        return autorRepository.findById(id).orElseThrow(() -> new RuntimeException("AUTOR no encontrado"));
    }
    public void borrar(int id) throws RuntimeException {
        Autor autor = this.obtenerPorId(id); //Si no la encuentra lanza una excepción
        if (autor.getLibros().size() != 0) {
            throw new RuntimeException("No se puede borrar un autor con libros");
        }
        autorRepository.delete(autor);
    }
}
