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
        model.addAttribute("alumno", alumno);

        /*
        List<Alumno> alumnos;
        //alumnos.add(serviceAlumno.findById(dni));
        Clase clase = new Clase(2, "moto", 20.0);
        //Clase clase = serviceClase.findById(1); //el número es el id de la clase a la que quieres agregar alumno
        alumnos = clase.getAlumnoes();
        alumnos.add(serviceAlumno.findById(dni));
        clase.setAlumnoes(alumnos);
        //clase.setAlumnoes(alumnos);
        serviceClase.save(clase);
        *
         */
        return "infoAlumno";
    }

    @GetMapping("/index/clase/{dni}")
    public Object formRegistroClase(Model model, @PathVariable(name = "dni") String dni){
        List<Alumno> alumnos;
        //alumnos.add(serviceAlumno.findById(dni));
        //Clase clase = new Clase(2, "moto", 20.0);
        Clase clase = serviceClase.findById(1); //el número es el id de la clase a la que quieres agregar alumno
        alumnos = clase.getAlumnoes();
        alumnos.add(serviceAlumno.findById(dni));
        clase.setAlumnoes(alumnos);
        //clase.setAlumnoes(alumnos);
        serviceClase.save(clase);
        return new RedirectView("/index");
    }

}
