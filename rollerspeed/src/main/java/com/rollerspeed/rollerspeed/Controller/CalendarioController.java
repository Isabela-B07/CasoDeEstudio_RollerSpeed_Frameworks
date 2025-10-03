package com.rollerspeed.rollerspeed.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calendario")
public class CalendarioController {

    @GetMapping
    public String mostrarCalendario() {
        return "calendario/calendario";
    }
}


