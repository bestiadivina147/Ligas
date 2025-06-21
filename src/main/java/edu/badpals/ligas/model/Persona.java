package edu.badpals.ligas.model;

import jakarta.persistence.*;

@Entity
public class Persona {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pasaporte_id", referencedColumnName = "id")
    private Pasaporte pasaporte;

    public Persona() {
    }

    public Persona(Long id, String nombre, Pasaporte pasaporte) {
        this.id = id;
        this.nombre = nombre;
        this.pasaporte = pasaporte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pasaporte getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(Pasaporte pasaporte) {
        this.pasaporte = pasaporte;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", pasaporte=" + getPasaporte().toString() +
                '}';
    }
}
