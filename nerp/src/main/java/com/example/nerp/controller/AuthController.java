package com.example.nerp.controller;
import org.springframework.ui.Model;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class AuthController {
 @Autowired
    private DataSource psqlDataSource; // Ya debería estar configurado

    @GetMapping("/forgot-password")
    public String showResetForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String resetPassword(@RequestParam String username,
                                @RequestParam String newPassword,
                                Model model) {
        try (Connection conn = psqlDataSource.getConnection()) {
            String sql = "UPDATE users SET password = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, newPassword); // Idealmente deberías encriptarla
                stmt.setString(2, username);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    model.addAttribute("message", "Contraseña actualizada correctamente.");
                } else {
                    model.addAttribute("message", "Usuario no encontrado.");
                }
            }
        } catch (SQLException e) {
            model.addAttribute("message", "Error al actualizar la contraseña.");
            e.printStackTrace();
        }

        return "forgot-password";
    }
}
