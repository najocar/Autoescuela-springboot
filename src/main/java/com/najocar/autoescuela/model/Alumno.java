package com.najocar.autoescuela.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.io.Serializable;

@Data
@Entity
@Table(name = "alumnos")
public class Alumno implements Serializable {
    @Id
    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Column(name = "nombre", nullable = false)
    private String nombre;

}
