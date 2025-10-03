package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.ClaseModel;
import com.rollerspeed.rollerspeed.Model.EstudianteModel;
import com.rollerspeed.rollerspeed.Repository.ClaseRepository;
import com.rollerspeed.rollerspeed.Repository.InstructorRepository;
import com.rollerspeed.rollerspeed.Repository.EstudianteRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Tag(name = "Administración de clases", description = "Operaciones sobre el manejo de las clases")
@Controller
@RequestMapping("/clases")
public class ClaseController {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Mostrar lista de clases - Documentado
    @Operation(
        summary = "Obtener todas las clases registradas",
        description = "Devuelve la lista de clases registradas.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de clases obtenida correctamente",
                content = @Content(schema = @Schema(implementation = EstudianteModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    @GetMapping
    public String listarClases(Model model) {
        List<ClaseModel> clases = claseRepository.findAll();
        model.addAttribute("clases", clases);
        return "clase/listar_clases"; 
    }

    // Formulario de registro
    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("clase", new ClaseModel());
        model.addAttribute("instructores", instructorRepository.findAll());
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        return "clase/form_clase"; 
    }

    // Guardar clase - Documentado
    @Operation(
        summary = "Guardar clase",
        description = "Permite la creación de una nueva clase en el sistema. ",
        responses = {
            @ApiResponse(responseCode = "200", description = "Clase guardada correctamente",
                content = @Content(schema = @Schema(implementation = EstudianteModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del seridor")
        }
    )

    @PostMapping("/guardar")
    public String guardarClase(
            @Valid @ModelAttribute("clase") ClaseModel clase,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("instructores", instructorRepository.findAll());
            model.addAttribute("estudiantes", estudianteRepository.findAll());
            return "clase/form_clase"; 
        }

        claseRepository.save(clase);
        return "redirect:/clases";
    }

    // Eliminar clase
@Operation(
        summary = "Eliminar una clase",
        description = "Permite eliminar una clase registrada",
        responses = {
            @ApiResponse(responseCode = "200", description = "Clase eliminada correctamente. ",
                content = @Content(schema = @Schema(implementation = EstudianteModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )

    @GetMapping("/eliminar/{id}")
    public String eliminarClase(@PathVariable Long id) {
        claseRepository.deleteById(id);
        return "redirect:/clases";
    }
}
