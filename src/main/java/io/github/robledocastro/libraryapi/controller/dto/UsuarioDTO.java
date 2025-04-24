package io.github.robledocastro.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "campo obrigatorio")
        String login,
        @NotBlank(message = "campo obrigatorio")
        String senha,
        @NotBlank(message = "campo obrigatorio")
        @Email(message = "invalido")
        String email,
        List<String> roles) {
}
