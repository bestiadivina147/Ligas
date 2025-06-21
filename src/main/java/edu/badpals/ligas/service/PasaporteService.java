package edu.badpals.ligas.service;

import edu.badpals.ligas.model.Pasaporte;
import edu.badpals.ligas.repository.PasaporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasaporteService {
    @Autowired
    private PasaporteRepository pasaporteRepository;

    public Pasaporte savePasaporte(Pasaporte pasaporte) {
        return pasaporteRepository.save(pasaporte);
    }
}
