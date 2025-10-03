package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.EstudianteModel;
import com.rollerspeed.rollerspeed.Model.InstructorModel;
import com.rollerspeed.rollerspeed.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Tag(name = "Administracion de Estudiantes", description = "Operaciones sobre el manejo de los Estudiantes")
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

    // Guardar estudiante - Documentado
    @Operation(
        summary = "Guardar estudiante",
        description = "Permite la creación de un nuevo estudiante en el sistema. ",
        responses = {
            @ApiResponse(responseCode = "200", description = "Estudiante guardado correctamente",
                content = @Content(schema = @Schema(implementation = EstudianteModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del seridor")
        }
    )

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EstudianteModel estudiante, RedirectAttributes redirectAttributes) {
        estudianteService.guardar(estudiante);
        redirectAttributes.addFlashAttribute("success", "✅ Estudiante guardado correctamente");
        return "redirect:/estudiantes/listar";
    }

    // Listar estudiantes - Documentado
    @Operation(
        summary = "Obtener todos los estudiantes registrados",
        description = "Devuelve la lista de estudiantes registrados.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de estudiantes obtenida correctamente",
                content = @Content(schema = @Schema(implementation = EstudianteModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )  

    @GetMapping("/listar")
    public String listar(Model model) {
        List<EstudianteModel> estudiantes = estudianteService.listar();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiante/listar_estudiantes";
    }

    // Editar estudiante - Documentado
    @Operation(
        summary = "Editar un estudiante",
        description = "Permite editar un estudiante registrado. ",
        responses = {
            @ApiResponse(responseCode = "200", description = "Estudiante editado corectamente. ",
                content = @Content(schema = @Schema(implementation = EstudianteModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )

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

    // Eliminar estudiante - Documentado
    @Operation(
        summary = "Eliminar un estudiante",
        description = "Permite eliminar un estudiante registrado",
        responses = {
            @ApiResponse(responseCode = "200", description = "Estudiante eliminado correctamente. ",
                content = @Content(schema = @Schema(implementation = EstudianteModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
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
