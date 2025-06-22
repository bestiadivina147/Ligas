package edu.badpals.ligas.controller;

import edu.badpals.ligas.model.Departamento;
import edu.badpals.ligas.model.Empleado;
import edu.badpals.ligas.service.DepartamentoService;
import edu.badpals.ligas.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    DepartamentoService departamentoService;

    @GetMapping("/{id}")
    public String showMovCuenta(@PathVariable Long id, Model model) {
        Departamento departamento = departamentoService.obtenerPorId(id);
        List<Empleado> empleados = empleadoService.findByDep(departamento);
        if (departamento != null) {
            model.addAttribute("listaEmpleados", empleados);
            model.addAttribute("departamento", departamento);
            return "empleadoListView";
        }
        return "redirect:/departamentos/";
    }
    @GetMapping("/new/{id}")
    public String showNew(@PathVariable Long id, Model model) {
        Departamento departamento = departamentoService.obtenerPorId(id);
        if (departamento != null) {
            Empleado empleado = new Empleado();
            empleado.setDepartamento(departamento);
            model.addAttribute("empleadoForm", empleado);
            return "empleadoNewView"; // HTML del formulario de movimiento
        }
        return "redirect:/departamentos/";
    }
    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Empleado nuevoEmpleado, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "empleadoNewView";
        }

        Departamento departamento = nuevoEmpleado.getDepartamento();
        departamentoService.editar(departamento);
        empleadoService.save(nuevoEmpleado);

        return "redirect:/empleados/" + departamento.getId();
    }
    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable Long id) {
        try {
            Empleado empleado = empleadoService.obtenerPorId(id);
            Departamento departamento = empleado.getDepartamento();
            departamentoService.editar(departamento);
            empleadoService.borrar(id);
            return "redirect:/empleados/" + departamento.getId();
        } catch (RuntimeException e) {
            return "redirect:/departamentos/"  ;
        }
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Empleado empleado = empleadoService.obtenerPorId(id);
        if (empleado != null) {
            model.addAttribute("empleadoForm", empleado);
            return "empleadoEditView";
        } else {
            return "redirect:/departamentos/";
        }
    }
    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("empleadoForm") Empleado empleadoEditado, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/cuentas/";
        } else {
            Empleado original = empleadoService.obtenerPorId(empleadoEditado.getId());
            Departamento departamento = original.getDepartamento();
            departamentoService.editar(departamento);
            empleadoService.editar(empleadoEditado);
            return "redirect:/empleados/"+ departamento.getId();
        }
    }
}
