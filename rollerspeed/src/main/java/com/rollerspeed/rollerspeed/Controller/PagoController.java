package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.PagoModel;
import com.rollerspeed.rollerspeed.Service.PagoService;
import com.rollerspeed.rollerspeed.Service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public String listarPagos(Model model) {
        model.addAttribute("pagos", pagoService.listarTodos());
        return "pagos/listar_pagos";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("pago", new PagoModel());
        model.addAttribute("estudiantes", estudianteService.listar());
        return "pagos/form_pago";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("pago") @Valid PagoModel pago, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "pagos/form_pago";
        }

        boolean esNuevo = (pago.getId() == null);
        pagoService.guardar(pago);

        if (esNuevo) {
            attr.addFlashAttribute("success", "Pago registrado correctamente");
        } else {
            attr.addFlashAttribute("success", "Pago actualizado correctamente");
        }

        return "redirect:/pagos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes attr) {
        pagoService.eliminar(id);
        attr.addFlashAttribute("success", "Pago eliminado");
        return "redirect:/pagos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        PagoModel pago = pagoService.buscarPorId(id);
        model.addAttribute("pago", pago);
        model.addAttribute("estudiantes", estudianteService.listar());
        return "pagos/form_pago";
    }

}
