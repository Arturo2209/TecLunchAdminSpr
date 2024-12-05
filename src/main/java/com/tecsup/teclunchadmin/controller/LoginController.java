package com.tecsup.teclunchadmin.controller;

import com.tecsup.teclunchadmin.model.Usuario;
import com.tecsup.teclunchadmin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    // Muestra la página de login
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Verifica que tienes un archivo login.html en el directorio resources/templates
    }

    // Muestra la página de inicio después del login exitoso
    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String username = oAuth2User.getAttribute("name");  // Obtén el nombre del usuario
            String email = oAuth2User.getAttribute("email");  // Obtén el correo del usuario

            // Verificar si el usuario existe en la base de datos
            Optional<Usuario> usuarioOpt = usuarioService.findByCorreo(email);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                // Verificar si el usuario ya tiene un idInstitucional
                if (usuario.getIdInstitucional() == null || usuario.getIdInstitucional().isEmpty()) {
                    return "redirect:/askChangeIdInstitucional";  // Redirige a la página para cambiar el idInstitucional
                } else {
                    model.addAttribute("username", username);
                    model.addAttribute("email", email);
                    return "home";  // Redirige a la vista de inicio si ya tiene un idInstitucional
                }
            } else {
                // Si el usuario no existe, lo creamos
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(username);
                nuevoUsuario.setCorreo(email);
                nuevoUsuario.setRol("Estudiante"); // Asignar un rol por defecto
                usuarioService.save(nuevoUsuario); // Guardar el usuario en la base de datos
                model.addAttribute("username", username);
                model.addAttribute("email", email);
            }
        }
        return "home";  // Esta es la vista home.html
    }
}
