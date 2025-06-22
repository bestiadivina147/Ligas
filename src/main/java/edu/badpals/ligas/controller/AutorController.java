package edu.badpals.ligas.controller;

import edu.badpals.ligas.model.Autor;
import edu.badpals.ligas.model.Libro;
import edu.badpals.ligas.service.AutorService;
import edu.badpals.ligas.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    AutorService autorService;
    @Autowired
    LibroService libroService;

    @GetMapping({ "/", "" })
    public String showList(@RequestParam(required = false) Integer err, Model model) {
        if (err != null) {
            if (err == 1)
                model.addAttribute("msgError", "Error en autor");
            if (err == 2)
                model.addAttribute("msgError", "Error en libro");
        }
        List<Autor> autores = autorService.obtenerTodos();
        autores.forEach(a -> a.getLibros().size());
        model.addAttribute("listaAutores", autores);
        return "autorListView";
    }
    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("autorForm", new Autor());
        return "autorNewView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid @ModelAttribute("autorForm") Autor autorNuevo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "redirect:/autores/?err=1";
        }
        autorService.add(autorNuevo);
        return "redirect:/autores/";
    }
//    @GetMapping("/delete/{id}")
//    public String showDelete(@PathVariable int id) {
//        try {
//            Autor autor = autorService.obtenerPorId(id);
//            List<Libro> libros = autor.getLibros();
//            if(libros.isEmpty()){
//                autorService.borrar(id);
//                return "redirect:/autores/";
//            }
//            else {
//                return "redirect:/autores/?err=1";
//            }
//
//        } catch (RuntimeException e) {
//            return "redirect:/autores/?err=1";
//        }
//    }
    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable int id) {
        try {
            Autor autor = autorService.obtenerPorId(id);
            List<Libro> libros = autor.getLibros();
            for (Libro libro : libros){
               int idLibro = libro.getId();
               libroService.borrar(idLibro);
            }
            autorService.borrar(id);
            return "redirect:/autores/";

        } catch (RuntimeException e) {
            return "redirect:/autores/?err=1";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Autor autor = autorService.obtenerPorId(id);
        if (autor != null) {
            model.addAttribute("autorForm", autor);
            return "autorEditView";
        } else {
            return "redirect:/autor/err=1";
        }
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("cuentaForm") Autor autor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/autores/?err=1";
        } else {
            autorService.add(autor);
            return "redirect:/autores/";
        }
    }

}
