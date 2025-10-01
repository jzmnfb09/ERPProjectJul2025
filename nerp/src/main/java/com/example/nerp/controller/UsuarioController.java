package com.example.nerp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nerp.model.Usuario;
import com.example.nerp.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")

public class UsuarioController {
   
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/cambiar-password")
    public String cambiarPassword(
            @RequestParam("actual") String actual,
            @RequestParam("nueva") String nueva,
            @RequestParam("confirmar") String confirmar,
            HttpSession session,
            RedirectAttributes redirect) {

        // Obtener el usuario desde la sesión
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");

        if (usuarioSesion == null) {
            redirect.addFlashAttribute("error", "Sesión no válida. Inicia sesión de nuevo.");
            return "redirect:/login";
        }

        String username = usuarioSesion.getUsername();

        // Buscar usuario en la base de datos
        Usuario usuario = usuarioRepository.buscarPorUsername(username);
        if (usuario == null) {
            redirect.addFlashAttribute("error", "Usuario no encontrado.");
            return "redirect:/modulos";
        }

        // Validar contraseña actual
        if (!passwordEncoder.matches(actual, usuario.getPassword())) {
            redirect.addFlashAttribute("error", "La contraseña actual es incorrecta.");
            return "redirect:/modulos";
        }

        // Validar que la nueva contraseña y la confirmación coincidan
        if (!nueva.equals(confirmar)) {
            redirect.addFlashAttribute("error", "Las nuevas contraseñas no coinciden.");
            return "redirect:/modulos";
        }

        // Guardar nueva contraseña codificada
        String nuevaCodificada = passwordEncoder.encode(nueva);
        usuarioRepository.actualizarPassword(username, nuevaCodificada);

        // Enviar mensaje de éxito
        redirect.addFlashAttribute("mensaje", "Contraseña actualizada correctamente.");
        return "redirect:/modulos";
    }
}
