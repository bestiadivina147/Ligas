package edu.badpals.ligas.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Curso {
    @Id @GeneratedValue
    private Integer id;

    private String titulo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Curso() {
    }

    public Curso(Integer id, String titulo, List<Estudiante> estudiantes) {
        this.id = id;
        this.titulo = titulo;
        this.estudiantes = estudiantes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", estudiantes=" + getEstudiantes().toString() +
                '}';
    }
}
