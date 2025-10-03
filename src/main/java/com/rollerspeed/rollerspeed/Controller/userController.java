package com.rollerspeed.rollerspeed.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rollerspeed.rollerspeed.Model.userModel;
import com.rollerspeed.rollerspeed.Service.userService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class userController {
  
    @Autowired
    userService usuarioService;


    @GetMapping("/listar")
    public String listarUser(Model model) {
        List<userModel> usuarios = usuarioService.listarUser(); // o findAll()
        model.addAttribute("usuarios", usuarios);
        return "user/listar_user";
    }

    // Mostrar formulario para crear nuevo usuario
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new userModel()); // objeto vacío para el formulario
        return "/user/form_user";
    }

    // Guardar el usuario enviado por el formulario
    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") userModel usuario, BindingResult result) {
        
    //  La validación en Spring Boot se basa en un estándar que se llama Bean Validation (JSR 380),
    //  y la implementación más usada es Hibernate Validator.
        // Tres partes clave:
        // Anotaciones en la clase modelo → como reglas que pones en los campos.
        // Ej: @NotBlank, @Email, @Size, etc.
        // @Valid en el controlador → le dice a Spring: “Revisa que todo esté bien antes de guardar”.
        // BindingResult → es como una “hoja de errores” que guarda lo que el usuario hizo mal.
        // Thymeleaf en el HTML → para mostrarle al usuario los errores bonitos y en español.
        // <dependency>
		// 	<groupId>org.springframework.boot</groupId>
		// 	<artifactId>spring-boot-starter-validation</artifactId>
		// </dependency>

        // ¿Hubo errores de validación?
        if (result.hasErrors()) {
            return "/user/form_user"; // vuelve al formulario, con los errores
        }
        
        // Si no hay errores → guarda y redirige
        usuarioService.guardar(usuario); // guarda en BD
        System.out.println("¡Usuario válido! Guardando a : " + usuario.getName());
        return "redirect:/user/listar"; // redirige a la lista
    }

}