package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.InstructorModel;
import com.rollerspeed.rollerspeed.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/instructores")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    // 👉 Mostrar formulario de inscripción
    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("instructor", new InstructorModel());
        return "form_instructor"; // archivo HTML en templates
    }

    // 👉 Guardar instructor
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute InstructorModel instructor) {
        instructorService.guardar(instructor);
        return "redirect:/instructores/listar";
    }

    // 👉 Listar instructores
    @GetMapping("/listar")
    public String listar(Model model) {
        List<InstructorModel> instructores = instructorService.listar();
        model.addAttribute("instructores", instructores);
        return "listar_instructores"; // archivo HTML en templates
    }
}
