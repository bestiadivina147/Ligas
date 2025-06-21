package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Persona;
import edu.badpals.ligas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }
    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }
    public Optional<Persona> getPersonaById(Integer id) {
        return personaRepository.findById(id);
    }
}
