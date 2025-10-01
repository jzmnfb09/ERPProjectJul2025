package com.example.nerp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class batchController {
@PostMapping("/batch")
public String redirigirABatch() {
    return "batch"; // si tienes templates/batch.html
}
@PostMapping("/TablasT")
public String verTablasT() {
    return "tablasT"; // nombre de tu vista .html
}
}
