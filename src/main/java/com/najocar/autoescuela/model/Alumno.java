package com.najocar.autoescuela.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "alumnos")
public class Alumno implements Serializable {
    @Id
    @Column(name = "dni", nullable = false, unique = true, length = 200)
    private String dni;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "alumnoes")
    private List<Clase> clases = new ArrayList<>();

}
