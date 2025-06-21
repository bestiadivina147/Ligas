package edu.badpals.ligas.controller;

import edu.badpals.ligas.model.Pasaporte;
import edu.badpals.ligas.model.Persona;
import edu.badpals.ligas.service.PasaporteService;
import edu.badpals.ligas.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personas")
public class PersonaController implements ControllerInterface{
    @Autowired
    private PersonaService personaService;

    @Autowired
    private PasaporteService pasaporteService;

    @Override
    @GetMapping("/list")
    public String list(Model model) {
        List<Persona> personas = personaService.getAllPersonas();
        model.addAttribute("personas", personas);
        return "personas";
    }

    @Override
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("persona", new Persona()); // Objeto vacío para el formulario
        model.addAttribute("pasaporte", new Pasaporte());
        return "personaForm"; // Devuelve la plantilla
    }

    @Override
    @GetMapping("/ver/{id}")
    public String editarPersona(@PathVariable Integer id, Model model) {
        Optional<Persona> persona = personaService.getPersonaById(id);
        model.addAttribute("persona", persona);
        return "persona"; // este es el nombre del HTML
    }

    @Override
    public String delete(Integer id) {
        return "";
    }

    @PostMapping("/guardar")
    public String savePersona(@ModelAttribute Persona persona) {
        personaService.savePersona(persona);
        return "redirect:/personas/list";
    }
}
