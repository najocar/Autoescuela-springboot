package com.najocar.autoescuela.controllers;


import com.najocar.autoescuela.model.Alumno;
import com.najocar.autoescuela.model.Clase;
import com.najocar.autoescuela.services.ServiceAlumno;
import com.najocar.autoescuela.services.ServiceClase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ServiceAlumno serviceAlumno;

    @Autowired
    private ServiceClase serviceClase;

    public MainController(ServiceAlumno serviceAlumno, ServiceClase serviceClase){
        this.serviceAlumno = serviceAlumno;
        this.serviceClase = serviceClase;
    }

    @GetMapping("/index")
    public String inicio(Model model){
        List<Alumno> alumnos = serviceAlumno.getAllAlumnos();
        model.addAttribute("alumnos", alumnos);
        return "index";
    }

    @GetMapping("/index/registro")
    public String formAlumnos(Model model){
        model.addAttribute("alumno", new Alumno());
        return "registroAlumno";
    }

    @PostMapping("/index/registro")
    public Object saveAlumno(@ModelAttribute("alumno") @Validated Alumno alumno, Model model){
        if (serviceAlumno.existsAlumnoByDni(alumno)) {
            model.addAttribute("alerta", "Error, este DNI ya ha sido registrado");
            return "registroAlumno";
        }
        serviceAlumno.save(alumno);
        return new RedirectView("/index");
    }

    @DeleteMapping("alumnos/{dni}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable String dni) {
        serviceAlumno.removeAlumnoDni(dni);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/index/{dni}")
    public String infoAlumno(Model model, @PathVariable(name = "dni") String dni){
        Alumno alumno = serviceAlumno.findById(dni);
        List<Clase> clases = serviceClase.getAllClases();
        List<Clase> aux = serviceClase.getAllClases();
        if (!clases.isEmpty()){
            for (Clase clase: aux) {
                if(!clase.getAlumnoes().contains(alumno)){
                    clases.remove(clase);
                }
            }

        }
        model.addAttribute("alumno", alumno);
        model.addAttribute("clase", clases);
        model.addAttribute("clasesBotones", aux);
        return "infoAlumno";
    }

    @GetMapping("/index/{claseid}/{dni}")//en vez de clase, pasar id de clase
    public Object registroEnClase(Model model, @PathVariable(name = "dni") String dni, @PathVariable(name = "claseid") int claseid){
        model.addAttribute("dni", dni);
        Alumno alumno = serviceAlumno.findById(dni);
        List<Alumno> alumnos;
        Clase clase = serviceClase.findById(claseid); //el número es el id de la clase a la que quieres agregar alumno
        if (!clase.getAlumnoes().contains(alumno)){
            alumnos = clase.getAlumnoes();
            alumnos.add(alumno);
            clase.setAlumnoes(alumnos);
            serviceClase.save(clase);
        }

        return new RedirectView("/index/{dni}");
    }

    @GetMapping("/index/borrar/{claseid}/{dni}")
    public Object borrarDeClase(Model model, @PathVariable(name = "dni") String dni, @PathVariable(name = "claseid") int claseid){
        model.addAttribute("dni", dni);
        Alumno alumno = serviceAlumno.findById(dni);
        List<Alumno> alumnos;
        Clase clase = serviceClase.findById(claseid); //el número es el id de la clase a la que quieres agregar alumno
        if (clase.getAlumnoes().contains(alumno)){
            alumnos = clase.getAlumnoes();
            //alumnos.remove(clase.getAlumnoes().indexOf(serviceAlumno.findById(dni)));
            alumnos.remove(alumno);
            clase.setAlumnoes(alumnos);
            serviceClase.save(clase);
        }

        return new RedirectView("/index/{dni}");
    }

    @GetMapping ("/index/update/{dni}/{nombre}")
    public Object updateAlumno(Model model, @PathVariable(name = "dni") String dni, @PathVariable(name = "nombre") String nombre){
        Alumno alumno = serviceAlumno.findById(dni);
        alumno.setNombre(nombre);
        if (!serviceAlumno.existsAlumnoByDni(alumno)) {
            model.addAttribute("alerta", "Error, no existe el alumno");
            return new RedirectView("/index/{dni}");
        }
        serviceAlumno.save(alumno);
        return new RedirectView("/index/{dni}");
    }

}
