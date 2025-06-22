package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Concesionario;
import edu.badpals.ligas.repository.ConcesionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcesionarioService {

    @Autowired
    ConcesionarioRepository concesionarioRepository;

    public Concesionario add(Concesionario concesionario) {
        return concesionarioRepository.save(concesionario);
    }

    public List<Concesionario> obtenerTodos() {
        return concesionarioRepository.findAll();
    }

    public Concesionario obtenerPorId(String name) throws RuntimeException {
        return concesionarioRepository.findById(name).orElseThrow(() -> new RuntimeException("Concesionario no encontrado"));
    }

    public Concesionario editar(Concesionario concesionario) {
        return concesionarioRepository.save(concesionario);
    }

    public void borrar(String name) throws RuntimeException {
        Concesionario concesionario = this.obtenerPorId(name);
        if (!concesionario.getCoches().isEmpty())
            throw new RuntimeException("No se puede borrar una Concesionario con coches");
        concesionarioRepository.delete(concesionario);
    }

}
