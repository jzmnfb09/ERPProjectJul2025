package com.example.nerp.controller.kdoffsite;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.nerp.model.kdoffsite.PFYSTICHP;
import com.example.nerp.service.kdoffsite.PFCCASNKService;
import com.example.nerp.service.kdoffsite.PFOASNDCPService;
import com.example.nerp.service.kdoffsite.PFYSTICHPService;
import com.example.nerp.service.kdoffsite.PFYSTIHPService;

@Controller
@RequestMapping("/kdoffsite")
public class KDOffsiteController {

    private final PFYSTICHPService pfystichpService;
    private final PFYSTIHPService pfystihpService;
    private final PFCCASNKService pfccasnkService;
    private final PFOASNDCPService pfoasndcpService;

    public KDOffsiteController(
            PFYSTICHPService pfystichpService,
            PFYSTIHPService pfystihpService,
            PFCCASNKService pfccasnkService,
            PFOASNDCPService pfoasndcpService) {
        this.pfystichpService = pfystichpService;
        this.pfystihpService = pfystihpService;
        this.pfccasnkService = pfccasnkService;
        this.pfoasndcpService = pfoasndcpService;
    }

    @GetMapping
    public String mostrarTablas(@RequestParam(value = "casem", required = false) String casem, Model model) {
        System.out.println("case module: " + casem);
        if (casem != null && !casem.isEmpty()) {
            List<PFYSTICHP> resultados = pfystichpService.buscarPorCase(casem);
            model.addAttribute("pfystichp", resultados);
            model.addAttribute("casem", casem);
            System.out.println("Case Module encontrado:");
            resultados.forEach(System.out::println);
        } else {
            model.addAttribute("pfystichp", null);
            model.addAttribute("casem", "");
        }
        return "kdoffsite";
    }

    @PostMapping
    public String procesarConsulta(@RequestParam("casem") String casem) {
        // Redirige a la URL GET con el parámetro casem
        return "redirect:/kdoffsite?casem=" + casem;
    }

    @GetMapping("/tabla-pfystichp")
    public String obtenerTablaPfystichp(@RequestParam("casem") String casem, Model model) {
        model.addAttribute("pfystichp", pfystichpService.buscarPorCase(casem));
        return "fragments/kdoffsite/tabla-pfystichp :: tablaPfystichp";
    }

    @GetMapping("/tabla-pfystihp")
    public String obtenerTablaPfystihp(@RequestParam String casem, Model model) {
        model.addAttribute("pfystihp", pfystihpService.buscarPorASN(casem));
        return "fragments/kdoffsite/tabla-pfystihp :: tablaPfystihp";
    }

    @GetMapping("/tabla-pfccasnk")
    public String obtenerTablaPfccasnk(@RequestParam String casem, Model model) {
        model.addAttribute("pfccasnk", pfccasnkService.buscarPorASN(casem));
        return "fragments/kdoffsite/tabla-pfccasnk :: tablaPfccasnk";
    }

    @GetMapping("/tabla-pfoasndcp")
    public String obtenerTablaPfoasndcp(
            @RequestParam(required = false) String boxSerialNo,
            @RequestParam(required = false) String partNumber,
            Model model) {

        if (boxSerialNo == null || partNumber == null || boxSerialNo.isBlank() || partNumber.isBlank()) {
            // Si no hay datos aún (por ejemplo, al recargar la página)
            model.addAttribute("pfoasndcp", List.of());
            return "fragments/kdoffsite/tabla-pfoasndcp :: tablaPfoasndcp";
        }

        model.addAttribute("pfoasndcp", pfoasndcpService.buscarPorBoxYPart(boxSerialNo, partNumber));
        return "fragments/kdoffsite/tabla-pfoasndcp :: tablaPfoasndcp";
    }

    @PostMapping("/editar-pfystichp")
    public String editarPFYSTICHP(@RequestParam("casem") String casem,
            @RequestParam("newTcloc") String newTcloc,
            @RequestParam("newTcsts") String newTcsts) {
        pfystichpService.editarKD(casem, newTcloc, newTcsts);
        return "redirect:/kdoffsite?casem=" + casem;
    }

    @PostMapping("/editar-pfystihp")
    public String editarPFYSTIHP(@RequestParam("thasn") String thasn,
            @RequestParam("newThsts") String newThsts,
            @RequestParam("casem") String casem) {
        pfystihpService.editarEstado(thasn, newThsts);
        return "redirect:/kdoffsite?casem=" + casem;
    }

    @PostMapping("/baja")
    @ResponseBody
    public String ejecutarBaja(@RequestParam("casem") String casem) {
        try {
            pfystichpService.darBaja(casem);
            return "Case Module: " + casem + " dado de baja";
        } catch (Exception e) {
            e.printStackTrace();

            return "Error al realizar la baja del Case Module: " + casem;
        }
    }

    @PostMapping("/eliminar")
    public String eliminarCase(@RequestParam("casem") String casem) {
        pfystichpService.eliminarCase(casem);
        return "redirect:/kdoffsite";
    }

}
