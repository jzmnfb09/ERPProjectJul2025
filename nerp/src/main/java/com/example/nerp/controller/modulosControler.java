package com.example.nerp.controller;



import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class modulosControler {

@PostMapping("/logout")
public String cerrarSesion(HttpServletRequest request) {
    request.getSession().invalidate(); // Invalida la sesión
    return "redirect:/login"; // O a donde desees redirigir
}




}
