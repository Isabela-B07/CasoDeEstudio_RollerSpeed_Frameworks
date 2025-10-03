package com.rollerspeed.rollerspeed.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clases")
public class CalendarioController {

    // Mostrar la vista del calendario
    @GetMapping("/estudiantes/eventos")
    public String mostrarCalendario(Model model) {
        // No necesitamos enviar datos, la vista es estática por ahora
        return "calendario/calendario"; 
    }
}
