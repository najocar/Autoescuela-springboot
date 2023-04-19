package com.najocar.autoescuela.controllers;


import com.najocar.autoescuela.model.Alumno;
import com.najocar.autoescuela.services.ServiceAlumno;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {
    private ServiceAlumno serviceAlumno;

    public MainController(ServiceAlumno serviceAlumno){
        this.serviceAlumno = serviceAlumno;
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
            model.addAttribute("alerta", "ha ocurrido un error");
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
}
