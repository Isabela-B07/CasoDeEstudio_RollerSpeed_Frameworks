package com.rollerspeed.rollerspeed.Controller;

import com.rollerspeed.rollerspeed.Model.ClaseModel;
import com.rollerspeed.rollerspeed.Model.userModel;
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

   
    @GetMapping("/listar")
    @ResponseBody
    public List<ClaseModel> listarClases() {
        return claseService.listar();
    }

    @GetMapping("/estudiantes/eventos")
    public String eventosEstudiantes(userModel user) {
        return "calendario/calendario";
    }


}
