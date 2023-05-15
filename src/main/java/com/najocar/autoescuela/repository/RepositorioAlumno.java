package com.najocar.autoescuela.repository;

import com.najocar.autoescuela.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositorioAlumno extends JpaRepository<Alumno, Long> {

    @Query(value = "SELECT * from alumnos", nativeQuery = true)
    public List<Alumno> findAll();

    @Query(value = "SELECT * from alumnos where dni = ?1", nativeQuery = true)
    public Optional<Alumno> findByDni(String dni);

    void deleteByDni(String nombre);

    Boolean existsAlumnoByDni(String dni);
}
