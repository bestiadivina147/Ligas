package edu.badpals.ligas.model;

import jakarta.persistence.*;

@Entity
public class Coche {

    @Id
    @GeneratedValue
    private int id;

    private String marca;
    private String modelo;
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "concesionario_nombre")
    private Concesionario concesionario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
}

