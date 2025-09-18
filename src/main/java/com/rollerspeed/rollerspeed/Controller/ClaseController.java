package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.ClaseModel;
import com.rollerspeed.rollerspeed.Service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/clases")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    // -------------------------
    // LISTAR CLASES (para probar en navegador)
    // -------------------------
    @GetMapping("/listar")
    @ResponseBody
    public List<ClaseModel> listarClases() {
        return claseService.listar();
    }

    // -------------------------
    // ENDPOINT: EVENTOS ESTUDIANTES
    // -------------------------
    @GetMapping("/estudiantes/eventos")
    @ResponseBody
    public List<Map<String, Object>> eventosEstudiantes() {
        List<Map<String, Object>> eventos = new ArrayList<>();

        // 🔹 Aquí deberías traer las clases reales desde la BD
        // Ejemplo con clases ficticias:
        Map<String, Object> evento1 = new HashMap<>();
        evento1.put("title", "Clase de Matemáticas");
        evento1.put("start", "2025-09-20T10:00:00");
        evento1.put("end", "2025-09-20T12:00:00");
        eventos.add(evento1);

        Map<String, Object> evento2 = new HashMap<>();
        evento2.put("title", "Clase de Inglés");
        evento2.put("start", "2025-09-22T14:00:00");
        evento2.put("end", "2025-09-22T16:00:00");
        eventos.add(evento2);

        return eventos;
    }

    // -------------------------
    // ENDPOINT: EVENTOS INSTRUCTORES
    // -------------------------
    @GetMapping("/instructores/eventos")
    @ResponseBody
    public List<Map<String, Object>> eventosInstructores() {
        List<Map<String, Object>> eventos = new ArrayList<>();

        // 🔹 Aquí deberías traer las clases reales desde la BD
        // Ejemplo con clases ficticias:
        Map<String, Object> evento1 = new HashMap<>();
        evento1.put("title", "Entrenamiento de Programación");
        evento1.put("start", "2025-09-21T09:00:00");
        evento1.put("end", "2025-09-21T11:00:00");
        eventos.add(evento1);

        Map<String, Object> evento2 = new HashMap<>();
        evento2.put("title", "Asesoría de Proyectos");
        evento2.put("start", "2025-09-23T15:00:00");
        evento2.put("end", "2025-09-23T17:00:00");
        eventos.add(evento2);

        return eventos;
    }
}
