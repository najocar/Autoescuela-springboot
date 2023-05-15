package com.najocar.autoescuela.services;

import com.najocar.autoescuela.model.Alumno;

import java.util.List;
import java.util.Optional;

public interface InterfaceAlumno {

    List<Alumno> getAllAlumnos();
    public Alumno findById(String dni);
}
