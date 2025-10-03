package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.ClaseModel;
import com.rollerspeed.rollerspeed.Model.EstudianteModel;
import com.rollerspeed.rollerspeed.Repository.ClaseRepository;
import com.rollerspeed.rollerspeed.Repository.InstructorRepository;
import com.rollerspeed.rollerspeed.Repository.EstudianteRepository;
import com.rollerspeed.rollerspeed.Service.ClaseService;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.util.Set;


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

    @Autowired
    private ClaseService claseService;

    // Mostrar lista de clases
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

    // Guardar clase
    @Operation(
        summary = "Guardar clase",
        description = "Permite la creación de una nueva clase en el sistema.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Clase guardada correctamente",
                content = @Content(schema = @Schema(implementation = EstudianteModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    
    @PostMapping("/guardar")
public String guardarClase(
        @Valid @ModelAttribute("clase") ClaseModel clase,
        BindingResult result,
        @RequestParam(value = "estudiantesIds", required = false) List<Long> estudiantesIds,
        Model model
) {
    if (result.hasErrors()) {
        model.addAttribute("instructores", instructorRepository.findAll());
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        return "clase/form_clase";
    }

    // Asociar estudiantes seleccionados (si hay)
    if (estudiantesIds != null && !estudiantesIds.isEmpty()) {
        Set<EstudianteModel> estudiantesSeleccionados = new HashSet<>(estudianteRepository.findAllById(estudiantesIds));
        clase.setEstudiantes(estudiantesSeleccionados);
    }

    claseRepository.save(clase);
    return "redirect:/clases";
}


    // Eliminar clase
    @Operation(
        summary = "Eliminar una clase",
        description = "Permite eliminar una clase registrada",
        responses = {
            @ApiResponse(responseCode = "200", description = "Clase eliminada correctamente.",
                content = @Content(schema = @Schema(implementation = EstudianteModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    @GetMapping("/eliminar/{id}")
    public String eliminarClase(@PathVariable Long id) {
        claseRepository.deleteById(id);
        return "redirect:/clases";
    }

    // Endpoint para calendario dinámico
    @GetMapping("/api")
@ResponseBody
public List<Map<String, Object>> obtenerClasesComoEventos() {
    List<ClaseModel> clases = claseService.listar();
    List<Map<String, Object>> eventos = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a"); // 12-hour format con AM/PM

    for (ClaseModel clase : clases) {
        Map<String, Object> evento = new HashMap<>();
        evento.put("title", clase.getNombre());
        evento.put("start", clase.getFechaHora().toString());
        evento.put("hora", clase.getFechaHora().toLocalTime().format(formatter)); // 👈 Aquí se ve AM/PM
        evento.put("instructor", clase.getInstructor().getNombre());
        evento.put("description", clase.getDescripcion());
        evento.put("color", "#764ba2");
        eventos.add(evento);
    }

    return eventos;
}

}
