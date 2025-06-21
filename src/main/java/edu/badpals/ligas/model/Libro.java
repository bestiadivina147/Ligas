package edu.badpals.ligas.model;

import jakarta.persistence.*;


@Entity
public class Libro {
    @Id @GeneratedValue
    private Integer id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {
    }

    public Libro(Integer id, String titulo, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", autor=" + getAutor().toString() +
                '}';
    }
}