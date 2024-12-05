package com.tecsup.teclunchadmin.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class ErrorController {

    // Este método captura todos los errores y los maneja
    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());  // Agrega el mensaje de error al modelo
        ex.printStackTrace();  // Imprime el stack trace en la consola para depuración
        return "error";  // Retorna la vista error.html
    }

    @RequestMapping("/error")
    public String errorPage(Model model) {
        model.addAttribute("error", "An unexpected error occurred");
        return "error";  // Página de error
    }
}
