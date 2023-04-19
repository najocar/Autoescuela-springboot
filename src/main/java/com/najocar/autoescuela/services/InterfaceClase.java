package com.najocar.autoescuela.services;

import com.najocar.autoescuela.model.Clase;

import java.util.List;

public interface InterfaceClase {

    List<Clase> getAllClases();
    public Clase findById(int id);
}
