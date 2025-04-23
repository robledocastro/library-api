package io.github.robledocastro.libraryapi.controller.mappers;

import io.github.robledocastro.libraryapi.controller.dto.UsuarioDTO;
import io.github.robledocastro.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
