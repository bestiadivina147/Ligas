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

@Controller
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    LibroService libroService;
    @Autowired
    AutorService autorService;

    @GetMapping("/{id}")
    public String showMovCuenta(@PathVariable int id, Model model) {
        Autor autor = autorService.obtenerPorId(id);
        if (autor != null) {
            model.addAttribute("listaLibros", autor.getLibros());
            model.addAttribute("autor", autor);
            return "libroListView";
        }
        return "redirect:/autores/list";
    }

    @GetMapping("/new/{id}")
    public String showNew(@PathVariable int id, Model model) {
        Autor autor = autorService.obtenerPorId(id);
        if (autor != null) {
            Libro libro = new Libro();
            libro.setAutor(autor);
            model.addAttribute("libroForm", libro);
            return "libroNewView"; // HTML del formulario de movimiento
        }
        return "redirect:/autores/";
    }
    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Libro nuevoLibro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "libroNewView";
        }

        Autor autor = nuevoLibro.getAutor();
        autor.getLibros().add(nuevoLibro);
        //autorService.add(autor);
        libroService.save(nuevoLibro);

        return "redirect:/libros/" + autor.getId();
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable int id) {
        try {
            Libro libro = libroService.obtenerPorId(id);
            Autor autor = libro.getAutor();
            autor.getLibros().remove(libro);
            libroService.borrar(id);
            return "redirect:/libros/" + autor.getId();
        } catch (RuntimeException e) {
            return "redirect:/autores/"  ;
        }
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Libro libro = libroService.obtenerPorId(id);
        Autor autor = libro.getAutor();
        if (libro != null) {
            model.addAttribute("libroForm", libro);
            return "libroEditView";
        } else {
            return "redirect:/libros/" + autor.getId();
        }
    }
    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("libroForm") Libro libroEditado, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/autores/";
        } else {
            Libro original = libroService.obtenerPorId(libroEditado.getId());
            Autor autor = original.getAutor();
//            autor.getLibros().remove(original);
//            autor.getLibros().add(libroEditado);
//
//
//            autorService.add(autor);


            libroService.editar(libroEditado);
            return "redirect:/libros/"+ autor.getId();
        }
    }
}
