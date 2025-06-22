package edu.badpals.ligas.controller;

import edu.badpals.ligas.model.Concesionario;
import edu.badpals.ligas.service.ConcesionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/concesionarios")
public class ConcesionarioController {
    @Autowired
    private ConcesionarioService concesionarioService;

    @GetMapping({ "/", "" })
    public String showList(@RequestParam(required = false) Integer err, Model model) {
        if (err != null) {
            if (err == 1)
                model.addAttribute("msgError", "Error en concesionario");
            if (err == 2)
                model.addAttribute("msgError", "Error en coche");
            if (err == 3)
                model.addAttribute("msgError", "Ya hay un coche con esa matricula");
        }
        model.addAttribute("listaConcesionarios", concesionarioService.obtenerTodos());
        return "concesionarioListView";
    }
    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("concesionarioForm", new Concesionario());
        return "concesionarioNewView";
    }
    @PostMapping("/new/submit")
    public String showNewSubmit(
            @Valid @ModelAttribute("concesionarioForm") Concesionario nuevoConcesionario,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "redirect:/concesionarios/?err=1";
        concesionarioService.add(nuevoConcesionario);
        return "redirect:/concesionarios/";
    }
    @GetMapping("/delete/{name}")
    public String showDelete(@PathVariable String name) {
        try {
            Concesionario concesionario = concesionarioService.obtenerPorId(name);
            if (concesionario.getCoches().isEmpty()) {
                concesionarioService.borrar(name);
                return "redirect:/concesionarios/";
            }
            return "redirect:/concesionarios/";
        } catch (RuntimeException e) {
            return "redirect:/concesionarios/?err=1";
        }
    }
    @GetMapping("/edit/{name}")
    public String showEditForm(@PathVariable String name, Model model) {
        Concesionario concesionario = concesionarioService.obtenerPorId(name);
        if (concesionario != null) {
            model.addAttribute("concesionarioForm", concesionario);
            return "concesionarioEditView";
        } else {
            return "redirect:/concesionarios/err=1";
        }
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("concesionarioForm") Concesionario concesionario, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/concesionarios/?err=1";
        } else {
            concesionarioService.editar(concesionario);
            return "redirect:/concesionarios/";
        }
    }

}
