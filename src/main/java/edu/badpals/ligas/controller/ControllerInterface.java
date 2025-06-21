package edu.badpals.ligas.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ControllerInterface {
    public String list(Model model);
    public String showForm(Model model);
    public String editarPersona(@PathVariable Integer id, Model model);
    public String delete(@PathVariable Integer id);

}
