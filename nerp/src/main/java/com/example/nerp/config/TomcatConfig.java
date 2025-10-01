package com.example.nerp.config;

import org.apache.catalina.Context;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {
@Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return factory -> factory.addContextCustomizers((Context context) -> {
            // Desactiva completamente URL rewriting para JSESSIONID
            context.setUseHttpOnly(true); // seguridad
            context.setCookies(true);     // fuerza uso de cookies
            context.setSessionCookiePath("/"); // asegúrate de que el path sea correcto
        });
    }
}
