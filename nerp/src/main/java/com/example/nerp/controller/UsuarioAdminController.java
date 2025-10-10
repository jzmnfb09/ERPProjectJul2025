package com.example.nerp.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nerp.model.Usuario;
import com.example.nerp.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuarios")
public class UsuarioAdminController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioAdminController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String listarUsuarios(Model model, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null || !"SUPERADMIN".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }

        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario, HttpServletRequest request) {
        Usuario sessionUser = (Usuario) request.getSession().getAttribute("usuario");
        if (sessionUser == null || !"SUPERADMIN".equalsIgnoreCase(sessionUser.getRol())) {
            return "redirect:/login";
        }

        // Encriptar la contraseña antes de guardar
        String hashed = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setPassword(hashed);

        usuarioRepository.actualizar(usuario);

        return "redirect:/usuarios";
    }

    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("id") Long id, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null || !"SUPERADMIN".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }

        usuarioRepository.eliminarPorId(id.intValue()); // porque tu id es tipo int en la clase Usuario
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null || !"SUPERADMIN".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }

        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente == null) {
            return "redirect:/usuarios";
        }

        model.addAttribute("usuarioEditar", usuarioExistente);
        return "editar-usuario"; // archivo Thymeleaf nuevo
    }

    @PostMapping("/crear")
    public String crearNuevoUsuario(@RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpServletRequest request) {
        Usuario actual = (Usuario) request.getSession().getAttribute("usuario");
        if (actual == null || !"SUPERADMIN".equalsIgnoreCase(actual.getRol())) {
            return "redirect:/login";
        }

        Usuario existente = usuarioRepository.buscarPorUsernameregistro(username);
        if (existente != null) {
            model.addAttribute("error", "El usuario ya existe.");
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "usuarios";
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(username);
        String hashed = new BCryptPasswordEncoder().encode(password);
        nuevoUsuario.setPassword(hashed);

        usuarioRepository.insertarUsuarioNuevo(nuevoUsuario);

        model.addAttribute("mensaje", "Usuario creado exitosamente.");
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 🔥 Elimina todos los atributos de la sesión
        return "redirect:/login"; // Redirige al login
    }

}
