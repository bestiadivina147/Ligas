package edu.badpals.ligas.repository;

import edu.badpals.ligas.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();
}
