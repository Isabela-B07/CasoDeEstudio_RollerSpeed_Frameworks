package com.rollerspeed.rollerspeed.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //Esta notación indica que es una clase controladora
@RequestMapping("/")
public class indexController {

    @GetMapping("home")
    public String Home() {
        return "index";
    }

    @GetMapping("mision")
    public String mision() {
        return "mision"; // Carga mision.html
    }

    @GetMapping("vision")
    public String vision() {
        return "vision"; // Carga vision.html
    }

    @GetMapping("valores")
    public String valores() {
        return "valores"; // Carga valores.html
    }

    @GetMapping("servicios")
    public String servicios() {
        return "servicios"; // Carga servicios.html
    }

    @GetMapping("eventos")
    public String eventos() {
        return "eventos"; // Carga eventos.html
    }

    @GetMapping("galeria")
    public String galeria() {
        return "galeria"; // Carga galeria.html
    }
    
}




