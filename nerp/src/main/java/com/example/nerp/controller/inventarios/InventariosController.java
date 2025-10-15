package com.example.nerp.controller.inventarios;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nerp.model.inventarios.GCCINVDT00;
import com.example.nerp.service.inventarios.GCCINVDT00Service;

@Controller
@RequestMapping("/inventarios")
public class InventariosController {
    private final GCCINVDT00Service gccinvdt00Service;

    public InventariosController(
        GCCINVDT00Service gccinvdt00Service
    ){
        this.gccinvdt00Service = gccinvdt00Service;
    }

    @GetMapping
    public String mostrarLPN(@RequestParam(value = "lpn", required = false) String lpn, Model model){
        System.out.println("LPN: "+lpn);
        if(lpn != null && !lpn.isEmpty()){
            List<GCCINVDT00> resultados = gccinvdt00Service.buscarPorLPN(lpn);
            model.addAttribute("gccinvdt00", resultados);
            model.addAttribute("lpn", lpn);
            System.out.println("LPN no encontrada: ");
            resultados.forEach(System.out::println);
        }else{
            model.addAttribute("gccinvdt00", null);
            model.addAttribute("lpn", "");
        }

        return "inventarios";
    }

    @PostMapping
    public String procesarConsulta(@RequestParam("lpn") String lpn){
        return "redirect:/inventarios?lpn=" + lpn;
    }
}
