package com.example.nerp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nerp.model.GCCSRTMS00;
import com.example.nerp.model.GCCTRNMS00;
import com.example.nerp.model.GCMBTHDT00;
import com.example.nerp.model.GCMBTHHD00;
import com.example.nerp.service.GCCCASDT00Service;
import com.example.nerp.service.GCCCASHD00Service;
import com.example.nerp.service.GCCSRTMS00Service;
import com.example.nerp.service.GCCTRNMS00Service;
import com.example.nerp.service.GCMBTHDT00Service;
import com.example.nerp.service.GCMBTHHD00Service;
import com.example.nerp.service.T01OG1PService;
import com.example.nerp.service.T02OG2PService;
import com.example.nerp.service.T03OG3PService;
import com.example.nerp.service.T07SPRPService;

@Controller
@RequestMapping("/gestionTrenes")
public class GestionTrenesController {

    private final GCCTRNMS00Service gcctrnms00Service;
    private final GCCCASHD00Service gcccashd00Service;
    private final T03OG3PService t03og3pService;
    private final T02OG2PService t02og2pService;
    private final T01OG1PService t01og1pService;
    private final GCCCASDT00Service gcccasdt00Service;
    private final T07SPRPService t07sprpService;
    private final GCCSRTMS00Service gccsrtms00Service;
    private final GCMBTHDT00Service gcmbthdt00Service;
    private final GCMBTHHD00Service gcmbthhd00Service;

    public GestionTrenesController(
            GCCTRNMS00Service gcctrnms00Service,
            GCCCASHD00Service gcccashd00Service,
            T03OG3PService t03og3pService,
            T02OG2PService t02og2pService,
            T01OG1PService t01og1pService,
            GCCCASDT00Service gcccasdt00Service,
            T07SPRPService t07sprpService,
            GCCSRTMS00Service gccsrtms00Service,
            GCMBTHDT00Service gcmbthdt00Service,
            GCMBTHHD00Service gcmbthhd00Service) {
        this.gcctrnms00Service = gcctrnms00Service;
        this.gcccashd00Service = gcccashd00Service;
        this.t03og3pService = t03og3pService;
        this.t02og2pService = t02og2pService;
        this.t01og1pService = t01og1pService;
        this.gcccasdt00Service = gcccasdt00Service;
        this.t07sprpService = t07sprpService;
        this.gccsrtms00Service = gccsrtms00Service;
        this.gcmbthdt00Service = gcmbthdt00Service;
        this.gcmbthhd00Service = gcmbthhd00Service;
    }

    // Página principal con filtro
    @GetMapping
    public String mostrarConsulta(@RequestParam(value = "tipoFiltro", required = false) String tipoFiltro,
                                  @RequestParam(value = "filtro", required = false) String filtro,
                                  Model model) {
        System.out.println("tipoFiltro: " + tipoFiltro);
        System.out.println("filtro: " + filtro);
        model.addAttribute("tipoFiltro", tipoFiltro);
        model.addAttribute("filtro", filtro);
        if (tipoFiltro != null && filtro != null && !filtro.isEmpty()) {
            if ("tren".equals(tipoFiltro)) {
                List<GCCTRNMS00> trenes = gcctrnms00Service.buscarPorClave(filtro);
                System.out.println("Trenes encontrados:");
                trenes.forEach(System.out::println); // Esto usa tu nuevo toString()
                model.addAttribute("gcctrnms00", trenes);

                model.addAttribute("gcctrnms00", gcctrnms00Service.buscarPorClave(filtro));
                model.addAttribute("t03og3p", t03og3pService.buscarPorClave(filtro));
                model.addAttribute("t02og2p", t02og2pService.buscarPorClave(filtro));
                model.addAttribute("t01og1p", t01og1pService.buscarPorClave(filtro));
                model.addAttribute("gcccashd00", gcccashd00Service.buscarPorClave(filtro));
                model.addAttribute("gcccasdt00", gcccasdt00Service.buscarPorClave(filtro));
                model.addAttribute("t07sprp", t07sprpService.buscarPorClave(filtro));
                model.addAttribute("gccsrtms00", gccsrtms00Service.buscarPorClave(filtro));

                model.addAttribute("bth", gcmbthdt00Service.buscarPorClave(filtro));
                model.addAttribute("bthh", gcmbthhd00Service.buscarPorClave(filtro));
            } else if ("batch".equals(tipoFiltro)) {
                List<GCMBTHDT00> batch = gcmbthdt00Service.buscarPorClave(filtro);
                System.out.println("Batch encontrado:");
                batch.forEach(System.out::println); // Esto usa tu nuevo toString()
                model.addAttribute("gcmbthdt00", batch);

                model.addAttribute("bach", gcmbthdt00Service.buscarPorbatch(filtro));
                model.addAttribute("bachh", gcmbthhd00Service.buscarPorbatchh(filtro));
            }
        }
        return "gestionTrenes"; // tu plantilla HTML principal
    }

    // POST que redirige para mantener comportamiento limpio
    @PostMapping
    public String procesarConsulta(
            @RequestParam(value = "tipoFiltro", required = false) String tipoFiltro,
            @RequestParam(value = "filtro", required = false) String filtro) {
        return "redirect:/gestionTrenes?tipoFiltro=" + tipoFiltro + "&filtro=" + filtro;
    }

}
