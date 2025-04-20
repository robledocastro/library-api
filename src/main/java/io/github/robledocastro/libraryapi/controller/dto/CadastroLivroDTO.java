package io.github.robledocastro.libraryapi.controller.dto;

import io.github.robledocastro.libraryapi.model.GeneroLivro;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(
        @ISBN
        @NotBlank(message = "campo obrigatorio")
        String isbn,
        @NotBlank(message = "campo obrigatorio")
        String titulo,
        @NotNull(message = "campo obrigatorio")
        @Past(message = "nao pode ser uma data futura")
        LocalDate dataPublicacao,
        GeneroLivro genero,
        @Positive(message = "o preco nao pode ser nagativo")
        BigDecimal preco,
        @NotNull(message = "campo obrigatorio")
        UUID idAutor
        ) {
}
