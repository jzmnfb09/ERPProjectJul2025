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
        if (usuario == null || !"admin".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }

        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @PostMapping("/editar")
    public String actualizarUsuario(@RequestParam Long id,
                                    @RequestParam String username,
                                    @RequestParam(required = false) String password,
                                    Model model,
                                    HttpServletRequest request) {

        Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
        if (usuarioSesion == null || !"admin".equalsIgnoreCase(usuarioSesion.getRol())) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            model.addAttribute("error", "Usuario no encontrado");
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "usuarios";
        }

        usuario.setUsername(username);

        if (password != null && !password.isEmpty()) {
            // Actualizamos la contraseña solo si se ingresó algo
            String hashed = new BCryptPasswordEncoder().encode(password);
            usuario.setPassword(hashed);
        }

        usuarioRepository.actualizar(usuario);

        model.addAttribute("mensaje", "Usuario actualizado correctamente");
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }


    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("id") Long id, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null || !"admin".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }

        usuarioRepository.eliminarPorId(id.intValue()); // porque tu id es tipo int en la clase Usuario
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, HttpServletRequest request) {
        Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
        if (usuarioSesion == null || !"admin".equalsIgnoreCase(usuarioSesion.getRol())) {
            return "redirect:/login";
        }

        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente == null) {
            return "redirect:/usuarios";
        }

        model.addAttribute("usuarios", usuarioRepository.findAll()); // lista completa
        model.addAttribute("usuarioEditar", usuarioExistente); // para llenar el modal
        return "usuarios"; // volvemos a la misma página
    }


    @PostMapping("/crear")
    public String crearNuevoUsuario(@RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpServletRequest request) {
        Usuario actual = (Usuario) request.getSession().getAttribute("usuario");
        if (actual == null || !"admin".equalsIgnoreCase(actual.getRol())) {
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
