package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "user/registro";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(
            @RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("password") String password,
            @RequestParam("rol") String rol
    ) {
        usuarioService.crearUsuario(nombre, correo, password, rol);
        return "redirect:/user/login";
    }
}
