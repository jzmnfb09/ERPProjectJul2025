package com.example.nerp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.nerp.model.Usuario;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
public class ModuloController {
@GetMapping("/modulos")
public String mostrarmodulos(HttpSession session, Model model) {
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null) {
        return "redirect:/login";
    }

    // Enviar datos a la vista
    model.addAttribute("usuario", usuario);
    model.addAttribute("rol", usuario.getRol()); // 👈 esto permite usar "th:if" en el HTML

    System.out.println("✅ Usuario en sesión: " + usuario.getUsername());
    System.out.println("✅ Rol del usuario: " + usuario.getRol());

    return "modulos"; // Thymeleaf: templates/modulos.html
}

    @GetMapping("/TablasT")
    public String mostrarTablasT(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "TablasT"; // Thymeleaf: templates/TablasT.html
    }
    
}
