package edu.badpals.ligas.controller;

import edu.badpals.ligas.model.Autor;
import edu.badpals.ligas.model.Estudiante;
import edu.badpals.ligas.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")

public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;
    @GetMapping({ "/", "" })
    public String showList(@RequestParam(required = false) Integer err, Model model) {
        if (err != null) {
            if (err == 1)
                model.addAttribute("msgError", "Error en estudiantes");
            if (err == 2)
                model.addAttribute("msgError", "Error en cursos");
        }
        List<Estudiante> estudiantes = estudianteService.obtenerTodos();
        for (Estudiante estudiante : estudiantes){
            estudiante.getCursos();
        }
        model.addAttribute("listaEstudiantes", estudiantes);
        return "estudianteListView";
    }
    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("estudianteForm", new Estudiante());
        return "estudianteNewView";
    }
    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid @ModelAttribute("estudianteForm") Estudiante estudianteNuevo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "redirect:/estudiantes/?err=1";
        }
        estudianteService.add(estudianteNuevo);
        return "redirect:/estudiantes/";
    }
}
