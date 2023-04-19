package com.najocar.autoescuela.repository;

import com.najocar.autoescuela.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioClase extends JpaRepository<Clase, Long> {

    @Query(value = "select * from clases", nativeQuery = true)
    public List<Clase> findAll();

    @Query(value = "select * from clases where id = ?1", nativeQuery = true)
    public Clase findById(int id);
}
