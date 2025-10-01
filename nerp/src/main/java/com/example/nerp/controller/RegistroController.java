package com.example.nerp.controller;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nerp.model.Usuario;
import com.example.nerp.repository.UsuarioRepository;
import com.example.nerp.service.BitacoraService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

@Controller // ← NECESARIO para que Spring lo registre
public class RegistroController {


   
    private final UsuarioRepository usuarioRepository;
    private final BitacoraService bitacoraService;

   public RegistroController(UsuarioRepository usuarioRepository, BitacoraService bitacoraService) {
        this.usuarioRepository = usuarioRepository;
        this.bitacoraService = bitacoraService;
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro"; // Asegúrate de tener registro.html en templates
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String username,
                                   @RequestParam String password,
                                   HttpServletRequest request,  // ← esto es lo que te falta
                                   Model model) {

        // Validación: debe comenzar con PMX0 y tener 4 caracteres más (exactamente 8)
        if (!username.matches("^PMX0.{4}$")) {
            model.addAttribute("error", "Usuario no válido. Debe comenzar con 'PMX0' y su numero de nomina");
            return "registro";
        }

         Usuario existente = usuarioRepository.buscarPorUsernameregistro(username);
        if (existente != null) {
            model.addAttribute("error", "El usuario ya existe.");
            return "registro";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setPassword(encoder.encode(password));
        usuarioRepository.insertarUsuarioNuevo(nuevoUsuario);

        // Guardar usuario en sesión
        request.getSession().setAttribute("usuario", username);

        // Registrar bitácora
        bitacoraService.registrar(username, "LOGIN", "Usuario registrado y sesión iniciada");

        model.addAttribute("mensaje", "Usuario registrado exitosamente.");
        return "login"; // Redirige a login.html
    }
    
}
