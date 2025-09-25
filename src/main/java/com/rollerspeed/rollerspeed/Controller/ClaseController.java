package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.ClaseModel;
import com.rollerspeed.rollerspeed.Repository.ClaseRepository;
import com.rollerspeed.rollerspeed.Repository.InstructorRepository;
import com.rollerspeed.rollerspeed.Repository.EstudianteRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clases")
public class ClaseController {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Mostrar lista de clases
    @GetMapping
    public String listarClases(Model model) {
        List<ClaseModel> clases = claseRepository.findAll();
        model.addAttribute("clases", clases);
        return "clase/listar_clases"; // ✅ carpeta "clase"
    }

    // Formulario de registro
    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("clase", new ClaseModel());
        model.addAttribute("instructores", instructorRepository.findAll());
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        return "clase/form_clase"; // ✅ carpeta "clase"
    }

    // Guardar clase
    @PostMapping("/guardar")
    public String guardarClase(
            @Valid @ModelAttribute("clase") ClaseModel clase,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("instructores", instructorRepository.findAll());
            model.addAttribute("estudiantes", estudianteRepository.findAll());
            return "clase/form_clase"; // ✅ carpeta "clase"
        }

        claseRepository.save(clase);
        return "redirect:/clases";
    }

    // Eliminar clase
    @GetMapping("/eliminar/{id}")
    public String eliminarClase(@PathVariable Long id) {
        claseRepository.deleteById(id);
        return "redirect:/clases";
    }
}
