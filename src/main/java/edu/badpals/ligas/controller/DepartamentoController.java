package edu.badpals.ligas.controller;

import edu.badpals.ligas.model.Departamento;
import edu.badpals.ligas.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
    @Autowired
    DepartamentoService departamentoService;

    @GetMapping({ "/", "" })
    public String showList(@RequestParam(required = false) Integer err, Model model) {
        if (err != null) {
            if (err == 1)
                model.addAttribute("msgError", "Error en Departamento");
            if (err == 2)
                model.addAttribute("msgError", "Error en empleado");
            if (err == 3)
                model.addAttribute("msgError", "El departamento tiene empleados");
        }
        model.addAttribute("listaDepartamentos", departamentoService.obtenerTodos());
        return "departamentoListView";
    }
    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("departamentoForm", new Departamento());
        return "departamentoNewView";
    }
    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid @ModelAttribute("cuentaForm") Departamento nuevoDepartamento, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/departamentos/?err=1";
        departamentoService.add(nuevoDepartamento);
        return "redirect:/departamentos/";
    }
    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable Long id) {
        try {
            departamentoService.borrar(id);
            return "redirect:/departamentos/";

        } catch (RuntimeException e) {
            return "redirect:/departamentos/?err=3";
        }
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Departamento departamento = departamentoService.obtenerPorId(id);
        if (departamento != null) {
            model.addAttribute("departamentoForm", departamento);
            return "departamentoEditView";
        } else {
            return "redirect:/departamentos/err=1";
        }
    }
    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("departamentoForm") Departamento departamento,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/departamentos/?err=1";
        } else {
            departamentoService.editar(departamento);
            return "redirect:/departamentos/";
        }
    }
}
