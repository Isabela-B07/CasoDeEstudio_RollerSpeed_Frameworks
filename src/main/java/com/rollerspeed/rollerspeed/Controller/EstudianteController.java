package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.EstudianteModel;
import com.rollerspeed.rollerspeed.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Mostrar formulario de inscripción.
    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new EstudianteModel());
        return "estudiante/form_estudiante";
    }

    // Guardar estudiante.
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EstudianteModel estudiante, RedirectAttributes redirectAttributes) {
        estudianteService.guardar(estudiante);
        redirectAttributes.addFlashAttribute("success", "✅ Estudiante guardado correctamente");
        return "redirect:/estudiantes/listar";
    }

    // Listar estudiantes
    @GetMapping("/listar")
    public String listar(Model model) {
        List<EstudianteModel> estudiantes = estudianteService.listar();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiante/listar_estudiantes";
    }

    // Editar estudiante
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        EstudianteModel estudiante = estudianteService.obtenerPorId(id);
        if (estudiante == null) {
            redirectAttributes.addFlashAttribute("error", "⚠️ El estudiante no existe");
            return "redirect:/estudiantes/listar";
        }
        model.addAttribute("estudiante", estudiante);
        return "estudiante/form_estudiante";
    }

    // Eliminar estudiante
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            estudianteService.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "✅ Estudiante eliminado correctamente");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error",
                    "⚠️ No se puede eliminar el estudiante porque está relacionado con una clase");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "❌ Ocurrió un error al intentar eliminar el estudiante");
        }
        return "redirect:/estudiantes/listar";
    }
}
