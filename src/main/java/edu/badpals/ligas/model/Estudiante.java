package edu.badpals.ligas.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Estudiante {
    @Id @GeneratedValue
    private Integer id;

    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "estudiantes")

    private Set<Curso> cursos = new HashSet<>();

    public Estudiante() {
    }

    public Estudiante(Integer id, String nombre, Set<Curso> cursos) {
        this.id = id;
        this.nombre = nombre;
        this.cursos = cursos;
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

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", cursos=" + getCursos().toString() +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estudiante)) return false;
        Estudiante that = (Estudiante) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}