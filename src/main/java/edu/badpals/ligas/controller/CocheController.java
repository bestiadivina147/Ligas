package edu.badpals.ligas.controller;

import edu.badpals.ligas.model.Coche;
import edu.badpals.ligas.model.Concesionario;
import edu.badpals.ligas.service.CocheService;
import edu.badpals.ligas.service.ConcesionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coches")

public class CocheController {
    @Autowired
    CocheService cocheService;
    @Autowired
    ConcesionarioService concesionarioService;

    @GetMapping("/{name}")
    public String showCochesConce(@PathVariable String name, Model model) {
        Concesionario concesionario = concesionarioService.obtenerPorId(name);
        if (concesionario != null) {
            model.addAttribute("listaCoches", concesionario.getCoches());
            model.addAttribute("concesionario", concesionario);
            return "cocheListView";
        }
        return "redirect:/concesionarios/";
    }

    @GetMapping("/new/{name}")
    public String showNew(@PathVariable String name, Model model) {
        Concesionario concesionario = concesionarioService.obtenerPorId(name);
        if (concesionario != null) {
            Coche coche = new Coche();
            coche.setConcesionario(concesionario);
            model.addAttribute("cocheForm", coche);
            return "cocheNewView"; // HTML del formulario de movimiento
        }
        return "redirect:/coches/";
    }
    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Coche nuevoCoche, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cocheNewView";
        }

        Concesionario concesionario = nuevoCoche.getConcesionario();
        for (Coche coche : cocheService.findAll()){
            if(nuevoCoche.getMatricula().equals(coche.getMatricula())|| nuevoCoche.getModelo() == null
                    || nuevoCoche.getMarca() == null || nuevoCoche.getMatricula() == null){
                return "redirect:/coches/" + concesionario.getNombre();
            }
        }
            concesionario.getCoches().add(nuevoCoche);
            concesionarioService.editar(concesionario);
            cocheService.save(nuevoCoche);

        return "redirect:/coches/" + concesionario.getNombre();
    }
    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable int id) {
        try {
            Coche coche = cocheService.obtenerPorId(id);
            Concesionario concesionario = coche.getConcesionario();
            concesionarioService.editar(concesionario);
            cocheService.borrar(id);
            return "redirect:/coches/" + concesionario.getNombre();
        } catch (RuntimeException e) {
            return "redirect:/concesionarios/"  ;
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Coche coche = cocheService.obtenerPorId(id);
        if (coche != null) {
            model.addAttribute("cocheForm", coche);
            return "cocheEditView";
        } else {
            return "redirect:/coches/";
        }
    }
    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("cocheForm") Coche coche, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/concesionarios/";
        } else {
            Concesionario concesionario = coche.getConcesionario();

            if(coche.getModelo() == null|| coche.getMarca() == null || coche.getMatricula() == null){
                return "redirect:/coches/" + concesionario.getNombre();
            }
            concesionarioService.editar(concesionario);
            cocheService.editar(coche);
            return"redirect:/coches/"+ concesionario.getNombre();
        }
    }




}
