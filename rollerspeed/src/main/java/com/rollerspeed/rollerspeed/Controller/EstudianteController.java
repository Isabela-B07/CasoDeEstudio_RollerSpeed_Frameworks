package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.EstudianteModel;
import com.rollerspeed.rollerspeed.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // 👉 Mostrar formulario de inscripción
    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new EstudianteModel());
        return "form_estudiante"; // archivo HTML en templates
    }

    // 👉 Guardar estudiante
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EstudianteModel estudiante) {
        estudianteService.guardar(estudiante);
        return "redirect:/estudiantes/listar";
    }

    // 👉 Listar estudiantes
    @GetMapping("/listar")
    public String listar(Model model) {
        List<EstudianteModel> estudiantes = estudianteService.listar();
        model.addAttribute("estudiantes", estudiantes);
        return "listar_estudiantes"; // archivo HTML en templates
    }
}
