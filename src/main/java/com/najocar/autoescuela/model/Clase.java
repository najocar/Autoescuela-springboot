package com.najocar.autoescuela.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "clases")
public class Clase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @ManyToMany
    @JoinTable(name = "clases_alumnoes",
            joinColumns = @JoinColumn(name = "clase_id"),
            inverseJoinColumns = @JoinColumn(name = "alumnoes_dni"))

    private List<Alumno> alumnoes = new ArrayList<>();

}
