package com.najocar.autoescuela.services;

import com.najocar.autoescuela.model.Alumno;
import com.najocar.autoescuela.model.Clase;
import com.najocar.autoescuela.repository.RepositorioClase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceClase implements InterfaceClase{

    private RepositorioClase repoClase;

    public ServiceClase(RepositorioClase repoClase){
        this.repoClase = repoClase;
    }

    @Override
    public List<Clase> getAllClases() {
        return repoClase.findAll();
    }

    @Override
    public Clase findById(int id) {
        return repoClase.findById(id);
    }

    public void save(Clase clase){
        repoClase.save(clase);
    }
}
