package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Libro;
import edu.badpals.ligas.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    public void save(Libro libro) {
        libroRepository.save(libro);
    }

    public Libro obtenerPorId(int id) throws RuntimeException {
        return libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrada"));
    }

    public void borrar(int id) throws RuntimeException {
        Libro libro = this.obtenerPorId(id); //Si no la encuentra lanza una excepción
        libroRepository.delete(libro);
    }

    public Libro editar(Libro libro) {
        return libroRepository.save(libro);
    }


}
