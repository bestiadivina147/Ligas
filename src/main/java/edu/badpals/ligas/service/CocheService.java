package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Coche;
import edu.badpals.ligas.repository.CocheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheService {
    @Autowired
    CocheRepository cocheRepository;
    public void save(Coche coche) {
        cocheRepository.save(coche);
    }


    public Coche obtenerPorId(int id) throws RuntimeException {
        return cocheRepository.findById(id).orElseThrow(() -> new RuntimeException("coche no encontrado"));
    }

    public void borrar(int id) throws RuntimeException {
        Coche coche = this.obtenerPorId(id); //Si no la encuentra lanza una excepción
        cocheRepository.delete(coche);
    }

    public Coche editar(Coche coche) {
        return cocheRepository.save(coche);
    }
    public List<Coche> findAll() {
        return cocheRepository.findAll();
    }

}
