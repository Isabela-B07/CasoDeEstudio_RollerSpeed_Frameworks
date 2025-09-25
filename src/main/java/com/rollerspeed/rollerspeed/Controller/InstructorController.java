package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.InstructorModel;
import com.rollerspeed.rollerspeed.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/instructores")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    // Mostrar formulario de inscripción
    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("instructor", new InstructorModel());
        return "instructor/form_instructor";
    }

    // Guardar instructor
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute InstructorModel instructor, RedirectAttributes redirectAttributes) {
        instructorService.guardar(instructor);
        redirectAttributes.addFlashAttribute("success", "✅ Instructor guardado correctamente");
        return "redirect:/instructores/listar";
    }

    // Listar instructores
    @GetMapping("/listar")
    public String listar(Model model) {
        List<InstructorModel> instructores = instructorService.listar();
        model.addAttribute("instructores", instructores);
        return "instructor/listar_instructores";
    }

    // Editar instructor
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        InstructorModel instructor = instructorService.obtenerPorId(id);
        if (instructor == null) {
            redirectAttributes.addFlashAttribute("error", "⚠️ El instructor no existe");
            return "redirect:/instructores/listar";
        }
        model.addAttribute("instructor", instructor);
        return "instructor/form_instructor";
    }

    // Eliminar instructor
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            instructorService.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "✅ Instructor eliminado correctamente");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error",
                    "⚠️ No se puede eliminar el instructor porque está relacionado con una clase");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "❌ Ocurrió un error al intentar eliminar el instructor");
        }
        return "redirect:/instructores/listar";
    }
}
