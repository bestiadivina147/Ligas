package edu.badpals.ligas.model;

import jakarta.persistence.*;

@Entity
public class Pasaporte {
    @Id @GeneratedValue
    private Integer id;

    @Column(name = "numero", nullable = false)
    private String numero;

    @OneToOne(mappedBy = "pasaporte")
    private Persona persona;

    public Pasaporte() {
    }

    public Pasaporte(Integer id, String numero, Persona persona) {
        this.id = id;
        this.numero = numero;
        this.persona = persona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Pasaporte{" +
                "id=" + getId() +
                ", numero='" + getNumero() + getPersona().toString() +
                '}';
    }
}
