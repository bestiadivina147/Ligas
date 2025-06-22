package edu.badpals.ligas.controller;

import edu.badpals.ligas.model.Curso;
import edu.badpals.ligas.model.Estudiante;
import edu.badpals.ligas.service.CursoService;
import edu.badpals.ligas.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/estudiantes")

public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    CursoService cursoService;
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

    @GetMapping("/{id}/cursos")
    public String verEstudiantesDelCurso(@PathVariable Integer id, Model model) {
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        if (estudiante != null) {
            model.addAttribute("estudiante", estudiante);
            return "estudianteCursosView";
        }
        return "redirect:/estudiantes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        if (estudiante != null) {
            model.addAttribute("estudianteForm", estudiante);
            return "estudianteEditView";
        } else {
            return "redirect:/estudiantes/err=1";
        }
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("estudianteForm") Estudiante estudiante, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/estudiantes/?err=1";
        } else {
            Estudiante estudiante1 = estudianteService.obtenerPorId(estudiante.getId());
            estudiante.getCursos().addAll(estudiante1.getCursos());
            estudianteService.add(estudiante);
            List<Curso> cursos = estudiante1.getCursos();
            for(Curso curso : cursos){
                curso.getEstudiantes().remove(estudiante1);
                curso.getEstudiantes().add(estudiante);
                cursoService.add(curso);
            }

            return "redirect:/estudiantes/";
        }
    }
    @GetMapping("/delete/{id}")
    @Transactional
    public String eliminarEstudiante(@PathVariable int id) {
        try {
            Estudiante estudiante = estudianteService.obtenerPorId(id);
            List<Curso> cursos = estudiante.getCursos();

            for (Curso curso : cursos) {
                curso.getEstudiantes().remove(estudiante);
                cursoService.add(curso);
            }

            estudiante.getCursos().clear();
            estudianteService.add(estudiante);

            estudianteService.borrar(id);

            return "redirect:/estudiantes/";
        } catch (RuntimeException e) {
            return "redirect:/estudiantes/?err=1";
        }
    }
}
