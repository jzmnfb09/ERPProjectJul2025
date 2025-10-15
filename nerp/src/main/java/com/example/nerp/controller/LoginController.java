package com.example.nerp.controller;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.nerp.model.Usuario;
import com.example.nerp.repository.UsuarioRepository;
import com.example.nerp.service.BitacoraService;

import jakarta.servlet.http.HttpSession;



@Controller
public class LoginController {

private final UsuarioRepository usuarioRepository;
    private final BitacoraService bitacoraService;

    public LoginController(UsuarioRepository usuarioRepository, BitacoraService bitacoraService) {
        this.usuarioRepository = usuarioRepository;
        this.bitacoraService = bitacoraService;
    }

    @GetMapping("/login")
    public String mostrarFormulario() {
        return "login"; // Thymeleaf: login.html
    }

    @PostMapping("/login")
public String procesarLogin(@RequestParam String username,
                             @RequestParam String password,
                             HttpSession session,
                             Model model) {
    if (username.isBlank() || password.isBlank()) {
        model.addAttribute("error", "Por favor, completa todos los campos");
        return "login";
    }

    Usuario usuario = usuarioRepository.buscarPorUsername(username);

    if (usuario == null) {
        bitacoraService.registrar(username, "LOGIN_FALLIDO", "Usuario no encontrado");
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    } else {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, usuario.getPassword())) {
            session.setAttribute("usuario", usuario);

            System.out.println("🧪 Rol del usuario logueado: '" + usuario.getRol() + "'");

            // Cargar solo los módulos que el usuario tiene permiso
            List<String> modulos = usuarioRepository.obtenerModulosDeUsuario(usuario.getUsername());
            session.setAttribute("modulos", modulos);

            System.out.println("Módulos del usuario: " + modulos);


            if ("SUPERADMI".equalsIgnoreCase(usuario.getRol())) {
                return "redirect:/usuarios";
            } else {
                return "redirect:/modulos";
            }
        } else {
            
        }
    }

    model.addAttribute("error", "Usuario o contraseña incorrectos");
    return "login";
}



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        Object usuario = session.getAttribute("usuario");
        if (usuario != null) {
            String username = ((Usuario) usuario).getUsername();
            bitacoraService.registrar(username, "LOGOUT", "Cierre de sesión");
        }
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public String mostrarFormularioOlvidoContrasena() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String procesarCambioContrasena(@RequestParam String username,
                                           @RequestParam String newPassword,
                                           Model model) {
        Usuario usuario = usuarioRepository.buscarPorUsername(username);
        if (usuario != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            usuario.setPassword(encoder.encode(newPassword));
            usuarioRepository.actualizar(usuario);
            model.addAttribute("message", "Contraseña actualizada correctamente.");
            bitacoraService.registrar(username, "CAMBIO_PASSWORD", "El usuario actualizó su contraseña");
        } else {
            model.addAttribute("message", "Usuario no encontrado.");
            bitacoraService.registrar(username, "CAMBIO_PASSWORD_FALLIDO", "Intento con usuario no existente");
        }

        return "forgot-password";
    }
    
    
}   
 


