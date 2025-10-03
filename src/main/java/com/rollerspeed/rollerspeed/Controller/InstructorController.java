package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.InstructorModel;
import com.rollerspeed.rollerspeed.Service.InstructorService;
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

@Tag(name = "Administracion de Instructores", description = "Operaciones sobre el manejo de los instructores")
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

    // Guardar instructor - Documentado

    @Operation(
        summary = "Guardar instructor",
        description = "Permite la creación de un nuevo instructor en el sistema. ",
        responses = {
            @ApiResponse(responseCode = "200", description = "Instructor guardado correctamente",
                content = @Content(schema = @Schema(implementation = InstructorModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del seridor")
        }
    )
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute InstructorModel instructor, RedirectAttributes redirectAttributes) {
        instructorService.guardar(instructor);
        redirectAttributes.addFlashAttribute("success", "✅ Instructor guardado correctamente");
        return "redirect:/instructores/listar";
    }

    // Listar instructores - Documentado
     @Operation(
        summary = "Obtener todos los instructores registrados",
        description = "Devuelve la lista de instructores registrados.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de instructores obtenida correctamente",
                content = @Content(schema = @Schema(implementation = InstructorModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )  
    @GetMapping("/listar")
    public String listar(Model model) {
        List<InstructorModel> instructores = instructorService.listar();
        model.addAttribute("instructores", instructores);
        return "instructor/listar_instructores";
    }

    // Editar instructor - Documentado
    @Operation(
        summary = "Editar instructor",
        description = "Permite editar el instructor seleccionado",
        responses = {
            @ApiResponse(responseCode = "200", description = "Instructor editdo correctamente",
                content = @Content(schema = @Schema(implementation = InstructorModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    ) 
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

    // Eliminar instructor - Documentado
    @Operation(
    summary = "Elimina un instructor de la lista ",
    description = "Indicar el ID del instructor que se desea eliminar.",
    responses = {
        @ApiResponse(responseCode = "200", description = "Instructor eliminado correctamente",
            content = @Content(schema = @Schema(implementation = InstructorModel.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    }
)
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
