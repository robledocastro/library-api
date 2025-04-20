package io.github.robledocastro.libraryapi.controller.mappers;

import io.github.robledocastro.libraryapi.controller.dto.AutorDTO;
import io.github.robledocastro.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
