package com.example.nerp.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/consulta")
public class ConsultaGeneralController {

    private final GCCTRNMS00Service trnmService;
    private final T03OG3PService t03Service;
    private final T02OG2PService t02Service;
    private final T01OG1PService t01Service;
    private final GCCCASHD00Service chdService;
    private final GCCCASDT00Service cdtService;
    private final T07SPRPService t07Service;
    private final GCCSRTMS00Service smService;
    private final GCMBTHDT00Service bthservice;
    private final GCMBTHHD00Service bthhservice;

    public ConsultaGeneralController(
        GCCTRNMS00Service trnmService,
        T03OG3PService t03Service,
        T02OG2PService t02Service,
        T01OG1PService t01Service,
        GCCCASHD00Service chdService,
        GCCCASDT00Service cdtService,
        T07SPRPService t07Service,
        GCCSRTMS00Service smService,
        GCMBTHDT00Service bthservice,
        GCMBTHHD00Service bthhservice
    ) {
        this.trnmService = trnmService;
        this.t03Service = t03Service;
        this.t02Service = t02Service;
        this.t01Service = t01Service;
        this.chdService = chdService;
        this.cdtService = cdtService;
        this.t07Service = t07Service;
        this.smService = smService;
        this.bthservice = bthservice;
        this.bthhservice = bthhservice;
    }

    @GetMapping
    public String mostrarFormulario() {
        return "TablasT";
    }
@PostMapping
public String procesarConsulta(@RequestParam("tipoFiltro") String tipoFiltro,
                               @RequestParam("filtro") String filtro,
                               Model model) {
    model.addAttribute("filtro", filtro);
    model.addAttribute("tipoFiltro", tipoFiltro);

    if ("batch".equals(tipoFiltro)) {
        model.addAttribute("bach", bthservice.buscarPorbatch(filtro));
        model.addAttribute("bachh", bthhservice.buscarPorbatchh(filtro));
    } else if ("tren".equals(tipoFiltro)) {
        model.addAttribute("trenes", trnmService.buscarPorClave(filtro));
        model.addAttribute("t03", t03Service.buscarPorClave(filtro));
        model.addAttribute("t02", t02Service.buscarPorClave(filtro));
        model.addAttribute("t01", t01Service.buscarPorClave(filtro));
        model.addAttribute("chd", chdService.buscarPorClave(filtro));
        model.addAttribute("cdt", cdtService.buscarPorClave(filtro));
        model.addAttribute("t07", t07Service.buscarPorClave(filtro));
        model.addAttribute("sm", smService.buscarPorClave(filtro));
        model.addAttribute("bth", bthservice.buscarPorClave(filtro));
        model.addAttribute("bthh", bthhservice.buscarPorClave(filtro));
    }

    return "TablasT";
}
   
}