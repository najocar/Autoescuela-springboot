package com.najocar.autoescuela.services;

import com.najocar.autoescuela.model.Alumno;
import com.najocar.autoescuela.repository.RepositorioAlumno;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAlumno implements InterfaceAlumno{

    private RepositorioAlumno repoAlumno;

    public ServiceAlumno(RepositorioAlumno repoAlumno){
        this.repoAlumno = repoAlumno;
    }

    @Override
    public List<Alumno> getAllAlumnos() {
        return repoAlumno.findAll();
    }

    @Override
    public Optional<Alumno> findById(String dni) {
        return repoAlumno.findByDni(dni);
    }

    public void save(Alumno alumno){
        repoAlumno.save(alumno);
    }

    @Transactional
    public void removeAlumnoDni(String dni) {
        repoAlumno.deleteByDni(dni);
    }

    public Boolean existsAlumnoByDni(Alumno alumno){
        return repoAlumno.existsAlumnoByDni(alumno.getDni());
    }
}
