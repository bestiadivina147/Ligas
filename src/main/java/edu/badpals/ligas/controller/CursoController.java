package edu.badpals.ligas.controller;


import edu.badpals.ligas.model.Autor;
import edu.badpals.ligas.model.Curso;
import edu.badpals.ligas.model.Estudiante;
import edu.badpals.ligas.service.CursoService;
import edu.badpals.ligas.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    CursoService cursoService ;
    @Autowired
    EstudianteService estudianteService;

    @GetMapping({ "/", "" })
    public String showList(@RequestParam(required = false) Integer err, Model model) {
        if (err != null) {
            if (err == 1)
                model.addAttribute("msgError", "Error en curso");
            if (err == 2)
                model.addAttribute("msgError", "Error en estudiante");
        }
        List<Curso> cursos = cursoService.obtenerTodos();
        for (Curso curso : cursos){
            curso.getEstudiantes().size();
        }

        model.addAttribute("listaCursos", cursos);
        return "cursoListView";
    }
    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("cursoForm", new Curso());
        return "cursoNewView";
    }
    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid @ModelAttribute("cursoForm") Curso cursoNuevo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "redirect:/cursos/?err=1";
        }
        cursoService.add(cursoNuevo);
        return "redirect:/cursos/";
    }

    @GetMapping("/{id}/asignar-estudiantes")
    public String asignarEstudiantes(@PathVariable Integer id, Model model) {
        Curso curso = cursoService.obtenerPorId(id);
        if (curso != null) {
            curso.getEstudiantes().removeIf(e -> e == null || e.getId() == null);
            model.addAttribute("cursoForm", curso);
            model.addAttribute("listaEstudiantes", estudianteService.obtenerTodos());
            return "cursoEstudiantesFormView";
        }
        return "redirect:/cursos";
    }


    @PostMapping("/asignar-estudiantes")
    public String guardarEstudiantesAsignados(@ModelAttribute("cursoForm") Curso cursoForm) {
        Curso cursoReal = cursoService.obtenerPorId(cursoForm.getId());

        if (cursoReal != null) {
            List<Estudiante> estudiantesAsignados = new ArrayList<>();

            if (cursoForm.getEstudiantes() != null) {
                for (Estudiante parcial : cursoForm.getEstudiantes()) {
                    Estudiante real = estudianteService.obtenerPorId(parcial.getId());
                    if (real != null) {
                        estudiantesAsignados.add(real);
                    }
                }
            }

            cursoReal.setEstudiantes(estudiantesAsignados);
            cursoService.add(cursoReal);
        }

        return "redirect:/cursos";
    }

    @GetMapping("/{id}/estudiantes")
    public String verEstudiantesDelCurso(@PathVariable Integer id, Model model) {
        Curso curso = cursoService.obtenerPorId(id);
        if (curso != null) {
            model.addAttribute("curso", curso);
            return "cursoEstudiantesView";
        }
        return "redirect:/cursos";
    }


}
