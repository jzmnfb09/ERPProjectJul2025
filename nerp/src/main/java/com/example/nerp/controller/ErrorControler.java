package com.example.nerp.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorControler implements org.springframework.boot.web.servlet.error.ErrorController{
@RequestMapping("/error")
    public String handleError() {
        return "error"; // crea error.html en /templates
    }
}
