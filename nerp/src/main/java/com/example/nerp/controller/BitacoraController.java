package com.example.nerp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nerp.model.BitacoraDTO;
import com.example.nerp.model.BitacoraMovimiento;
import com.example.nerp.model.Usuario;
import com.example.nerp.service.BitacoraService;

import jakarta.servlet.http.HttpServletRequest;

@RestController // ← ¡Esto es esencial!
public class BitacoraController {
    private final BitacoraService bitacoraService;

    public BitacoraController(BitacoraService bitacoraService) {
        this.bitacoraService = bitacoraService;
    }

    @PostMapping("/registro-movimiento")
    public ResponseEntity<Void> registrarMovimiento(@RequestBody BitacoraDTO dto, HttpServletRequest request) {
        Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
        String nombreUsuario = (usuarioSesion != null) ? usuarioSesion.getUsername() : "DESCONOCIDO";

        // Evitar registrar el inicio de sesión
        String accion = dto.getAccion() != null ? dto.getAccion().toLowerCase().replace("í", "i").trim() : "";
        if (!accion.contains("inicio de sesion")) {
            bitacoraService.registrar(nombreUsuario, dto.getAccion(), dto.getDetalle(), dto.getTren());

        }

        return ResponseEntity.ok().build();
    }

    // 🔹 Consulta de movimientos (Thymeleaf)
    @Controller
    @RequestMapping("/bitacora")
    public static class BitacoraVistaController {

        private final BitacoraService bitacoraService;

        public BitacoraVistaController(BitacoraService bitacoraService) {
            this.bitacoraService = bitacoraService;
        }

        @GetMapping
        public String verBitacora(
                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
                @RequestParam(required = false) String usuario,
                @RequestParam(required = false) String tren,
                Model model) {
            List<BitacoraMovimiento> movimientos;

            // lógica de filtros combinados
            if (fecha != null && usuario != null && !usuario.isBlank() && tren != null && !tren.isBlank()) {
                movimientos = bitacoraService.buscarPorFechaUsuarioYTren(fecha, usuario, tren);
            } else if (fecha != null && usuario != null && !usuario.isBlank()) {
                movimientos = bitacoraService.buscarPorFechaYUsuario(fecha, usuario);
            } else if (fecha != null && tren != null && !tren.isBlank()) {
                movimientos = bitacoraService.buscarPorFechaYTren(fecha, tren);
            } else if (usuario != null && !usuario.isBlank() && tren != null && !tren.isBlank()) {
                movimientos = bitacoraService.buscarPorUsuarioYTren(usuario, tren);
            } else if (fecha != null) {
                movimientos = bitacoraService.buscarPorFecha(fecha);
            } else if (usuario != null && !usuario.isBlank()) {
                movimientos = bitacoraService.buscarPorUsuario(usuario);
            } else if (tren != null && !tren.isBlank()) {
                movimientos = bitacoraService.buscarPorTren(tren);
            } else {
                movimientos = bitacoraService.obtenerTodos();
            }

            model.addAttribute("filtroFecha", fecha);
            model.addAttribute("filtroUsuario", usuario);
            model.addAttribute("movimientos", movimientos);
            model.addAttribute("filtroTren", tren);
            return "bitacora";
        }

    }
}
