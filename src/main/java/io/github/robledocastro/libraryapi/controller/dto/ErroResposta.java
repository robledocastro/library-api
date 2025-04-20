package io.github.robledocastro.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrao(String mesnagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mesnagem, List.of());
    }

    public static ErroResposta conflito(String mesnagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mesnagem, List.of());
    }
}
