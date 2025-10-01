package com.example.nerp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nerp.service.BitacoraService;

import com.example.nerp.model.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/guardar")
public class ActualizarController {
private final JdbcTemplate jdbcTemplate;
private final BitacoraService bitacoraService;
    public ActualizarController(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbcTemplate,
         BitacoraService bitacoraService) {
        this.jdbcTemplate = jdbcTemplate;
        this.bitacoraService = bitacoraService;
    }
@PostMapping("/trnm")
public ResponseEntity<?> guardarCambiosTrnm(@RequestBody List<Map<String, String>> cambios,
                                            HttpServletRequest request) {
    HttpSession session = request.getSession();
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String nombreUsuario = (usuario != null) ? usuario.getUsername() : "DESCONOCIDO";

    for (Map<String, String> fila : cambios) {
        String pk = fila.get("ftrntrnñ");
        if (pk == null) continue;

        StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.GCCTRNMS00 SET ");
        List<Object> params = new ArrayList<>();

        StringBuilder cambiosBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : fila.entrySet()) {
            String key = entry.getKey();
            if (!key.equals("ftrntrnñ") && !key.startsWith("original_")) {
                String nuevoValor = entry.getValue();
                String valorOriginal = fila.get("original_" + key);

                // Armar SQL
                sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                params.add(nuevoValor);

                // Armar detalle para bitácora
                cambiosBuilder.append("Campo: ").append(key);
                cambiosBuilder.append(", Valor original: ").append(valorOriginal != null ? valorOriginal : "N/A");
                cambiosBuilder.append(", Valor nuevo: ").append(nuevoValor).append("; ");
            }
        }

        if (params.isEmpty()) continue; // si no hubo cambios, no actualizar ni registrar

        // Ejecutar SQL
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE \"FTRNTRNÑ\" = ?");
        params.add(pk);
        jdbcTemplate.update(sql.toString(), params.toArray());

        // Registrar en bitácora
        String detalle = String.format("Tabla: GCCTRNMS00 | Tren: %s | Cambios: %s", pk, cambiosBuilder.toString().trim());
        bitacoraService.registrar(nombreUsuario, "UPDATE_CAMPO", detalle, pk);
    }

    return ResponseEntity.ok().build();
}


   


    
    @PostMapping("/t03")
    public ResponseEntity<?> guardarCambiosT03(@RequestBody List<Map<String, String>> cambios) {
        for (Map<String, String> fila : cambios) {
            String pk = fila.get("spare3"); 
            if (pk == null) continue;

            StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.T03OG3P SET  ");
            List<Object> params = new ArrayList<>();

            fila.forEach((key, value) -> {
                if (!key.equals("spare3")) {
                    sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                    params.add(value);
                }
            });

            sql.setLength(sql.length() - 2);
            sql.append(" WHERE \"SPARE3\" = ?");
            params.add(pk);

             System.out.println("QUERY: " + sql);
            System.out.println("PARAMS: " + params);

            jdbcTemplate.update(sql.toString(), params.toArray());
        }

            return ResponseEntity.ok().build();
    }

    @PostMapping("/t02")
    public ResponseEntity<?> guardarCambiosT02(@RequestBody List<Map<String, String>> cambios) {
        for (Map<String, String> fila : cambios) {
            String pk = fila.get("spare3"); 
            if (pk == null) continue;

            StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.T02OG2P SET  ");
            List<Object> params = new ArrayList<>();

            fila.forEach((key, value) -> {
                if (!key.equals("spare3")) {
                    sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                    params.add(value);
                }
            });

            sql.setLength(sql.length() - 2);
            sql.append(" WHERE \"SPARE3\" = ?");
            params.add(pk);

            jdbcTemplate.update(sql.toString(), params.toArray());
        }

            return ResponseEntity.ok().build();
    }
    @PostMapping("/t01")
public ResponseEntity<?> guardarCambiosT01(@RequestBody List<Map<String, String>> cambios) {
     System.out.println("Recibiendo cambios T01: " + cambios); // 👈 Poner esto para verificar
    for (Map<String, String> fila : cambios) {
        String spare3 = fila.get("spare3");
        String tipo = fila.get("fcasplsp"); // S o L

        if (spare3 == null || tipo == null) continue;

        StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.T01OG1P SET ");
        List<Object> params = new ArrayList<>();

        // Recolectar campos para actualizar (excluyendo llaves)
        for (Map.Entry<String, String> entry : fila.entrySet()) {
            String key = entry.getKey();
            if (!"spare3".equals(key) && !"cntpsq".equals(key) && !"crtid".equals(key) && !"fcasplsp".equals(key)) {
                sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                params.add(entry.getValue());
            }
        }

        if (params.isEmpty()) continue; // No hay campos a actualizar

        // Quitar coma final
        sql.setLength(sql.length() - 2);

        // Construir WHERE según tipo
        if ("S".equalsIgnoreCase(tipo) && fila.get("cntpsq") != null) {
            sql.append(" WHERE \"SPARE3\" = ? AND \"CNTPSQ\" = ?");
            params.add(spare3);
            params.add(fila.get("cntpsq"));
        } else if ("L".equalsIgnoreCase(tipo) && fila.get("crtid") != null) {
            sql.append(" WHERE \"SPARE3\" = ? AND \"CRTID\" = ?");
            params.add(spare3);
            params.add(fila.get("crtid"));
        } else {
            continue; // No hay suficiente info para el WHERE
        }

        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    return ResponseEntity.ok().build();
}

   

    @PostMapping("/bthh")
    public ResponseEntity<?> guardarCambiosBthhd(@RequestBody List<Map<String, String>> cambios) {
        for (Map<String, String> fila : cambios) {
            String pk = fila.get("fbthbthñ");
            if (pk == null) continue;

            StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.GCMBTHHD00 SET ");
            List<Object> params = new ArrayList<>();

            fila.forEach((key, value) -> {
                if (!key.equals("fbthbthñ")) {
                    sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                    params.add(value);
                }
            });

            sql.setLength(sql.length() - 2); // quitar coma final
            sql.append(" WHERE  \"FBTHBTHÑ\" = ? ");
            params.add(pk);

            System.out.println("QUERY: " + sql);
            System.out.println("PARAMS: " + params);

            jdbcTemplate.update(sql.toString(), params.toArray());
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/chd")
    public ResponseEntity<?> guardarCambiosCashd(@RequestBody List<Map<String, String>> cambios) {
        for (Map<String, String> fila : cambios) {
            String pk = fila.get("fcastrnñ");
            if (pk == null) continue;

            StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.GCCCASHD00 SET ");
            List<Object> params = new ArrayList<>();

            fila.forEach((key, value) -> {
                if (!key.equals("fcastrnñ")) {
                    sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                    params.add(value);
                }
            });

            sql.setLength(sql.length() - 2); // quitar coma final
            sql.append(" WHERE  \"FCASTRNÑ\" = ? ");
            params.add(pk);

            jdbcTemplate.update(sql.toString(), params.toArray());
        }

        return ResponseEntity.ok().build();
    }

    //revisar xq no entra al metodo 
    @PostMapping("/cdt")
    public ResponseEntity<?> guardarCambiosCasdt(@RequestBody List<Map<String, String>> cambios) {
        for (Map<String, String> fila : cambios) {
            String fcastrnñ = fila.get("fcastrnñ");
            String fcaslpn = fila.get("fcaslpn");

            if (fcastrnñ == null || fcaslpn == null) continue;

            StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.GCCCASDT00 SET ");
            List<Object> params = new ArrayList<>();

            // Recolectar campos para actualizar (excluyendo las llaves)
            for (Map.Entry<String, String> entry : fila.entrySet()) {
                String key = entry.getKey();
                if (!"fcastrnñ".equals(key) && !"fcaslpn".equals(key)) {
                    sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                    params.add(entry.getValue());
                }
            }

            // Si no hay campos, salta esta iteración
            if (params.isEmpty()) continue;

            // Finaliza el query
            sql.setLength(sql.length() - 2); // quitar coma final
            sql.append(" WHERE \"FCASTRNÑ\" = ? AND \"FCASLPNÑ\" = ?");
            params.add(fcastrnñ);
            params.add(fcaslpn);

            System.out.println("QUERY: " + sql);
            System.out.println("PARAMS: " + params);
            
            jdbcTemplate.update(sql.toString(), params.toArray());
        
        }

        return ResponseEntity.ok().build();
    }    

    @PostMapping("/t07")
    public ResponseEntity<?> guardarCambiosT07(@RequestBody List<Map<String, String>> cambios) {
        for (Map<String, String> fila : cambios) {
            String spare3 = fila.get("spare3");
            String cntpsq = fila.get("cntpsq");

            if (spare3 == null || cntpsq == null) continue;

            StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.T07SPRP SET ");
            List<Object> params = new ArrayList<>();

            // Recolectar campos para actualizar (excluyendo las llaves)
            for (Map.Entry<String, String> entry : fila.entrySet()) {
                String key = entry.getKey();
                if (!"spare3".equals(key) && !"cntpsq".equals(key)) {
                    sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                    params.add(entry.getValue());
                }
            }

            // Si no hay campos, salta esta iteración
            if (params.isEmpty()) continue;

            // Finaliza el query
            sql.setLength(sql.length() - 2); // quitar coma final
            sql.append(" WHERE \"SPARE3\" = ? AND \"CNTPSQ\" = ?");
            params.add(spare3);
            params.add(cntpsq);
            System.out.println("QUERY: " + sql);
            System.out.println("PARAMS: " + params);

            jdbcTemplate.update(sql.toString(), params.toArray());
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sm")
    public ResponseEntity<?> guardarCambiosSM(@RequestBody List<Map<String, String>> cambios) {
        for (Map<String, String> fila : cambios) {
            String fsrttrn = fila.get("fsrttrn");
            String fsrtcids = fila.get("fsrtcids");

            if (fsrttrn == null || fsrtcids == null) continue;

            StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.GCCSRTMS00 SET ");
            List<Object> params = new ArrayList<>();

            // Recolectar campos para actualizar (excluyendo las llaves)
            for (Map.Entry<String, String> entry : fila.entrySet()) {
                String key = entry.getKey();
                if (!"fsrttrn".equals(key) && !"fsrtcids".equals(key)) {
                    sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                    params.add(entry.getValue());
                }
            }

            // Si no hay campos, salta esta iteración
            if (params.isEmpty()) continue;

            // Finaliza el query
            sql.setLength(sql.length() - 2); // quitar coma final
            sql.append(" WHERE \"FSRTTRNÑ\" = ? AND \"FSRTCIDÑ\" = ?");
            params.add(fsrttrn);
            params.add(fsrtcids);
            System.out.println("QUERY: " + sql);
            System.out.println("PARAMS: " + params);

            jdbcTemplate.update(sql.toString(), params.toArray());
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/bth")
    public ResponseEntity<?> guardarCambiosBth(@RequestBody List<Map<String, String>> cambios) {
        for (Map<String, String> fila : cambios) {
            String fbtdbth = fila.get("fbtdbth");
            String fbtdtrn = fila.get("fbtdtrn");

            if (fbtdbth == null || fbtdtrn == null) continue;

            StringBuilder sql = new StringBuilder("UPDATE GCMPRDBASD.GCMBTHDT00 SET ");
            List<Object> params = new ArrayList<>();

            // Recolectar campos para actualizar (excluyendo las llaves)
            for (Map.Entry<String, String> entry : fila.entrySet()) {
                String key = entry.getKey();
                if (!"fbtdbth".equals(key) && !"fbtdtrn".equals(key)) {
                    sql.append("\"").append(key.toUpperCase()).append("\" = ?, ");
                    params.add(entry.getValue());
                }
            }

            // Si no hay campos, salta esta iteración
            if (params.isEmpty()) continue;

            // Finaliza el query
            sql.setLength(sql.length() - 2); // quitar coma final
            sql.append(" WHERE \"FBTDBTHÑ\" = ? AND \"FBTDTRNÑ\" = ?");
            params.add(fbtdbth);
            params.add(fbtdtrn);
            System.out.println("QUERY: " + sql);
            System.out.println("PARAMS: " + params);

            jdbcTemplate.update(sql.toString(), params.toArray());
        }

        return ResponseEntity.ok().build();
    }
    
}
