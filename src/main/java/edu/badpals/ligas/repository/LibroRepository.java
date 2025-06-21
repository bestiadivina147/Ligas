package edu.badpals.ligas.repository;

import edu.badpals.ligas.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Integer> {
    List<Libro> findByAutor_Id(Integer autorId);

}
