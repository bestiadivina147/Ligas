package edu.badpals.ligas.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Autor {
    @Id @GeneratedValue
    private Integer id;

    private String nombre;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE,mappedBy = "autor")
    private List<Libro> libros = new ArrayList<>();

    public Autor() {
    }

    public Autor(Integer id, String nombre, List<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.libros = libros;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", libros=" + getLibros().toString() +
                '}';
    }
}
