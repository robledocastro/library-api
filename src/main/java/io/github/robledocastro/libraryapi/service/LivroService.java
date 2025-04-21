package io.github.robledocastro.libraryapi.service;

import io.github.robledocastro.libraryapi.model.GeneroLivro;
import io.github.robledocastro.libraryapi.model.Livro;
import io.github.robledocastro.libraryapi.repository.LivroRepository;
import io.github.robledocastro.libraryapi.repository.specs.LivroSpecs;
import io.github.robledocastro.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.robledocastro.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final LivroValidator validator;

    public Livro salvar(Livro livro) {
        validator.validar(livro);
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Livro livro){
        repository.delete(livro);
    }

    public List<Livro> pesquisa(String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao){

        // select * from livro where isbn = :isbn and genero = :genero
        //Specification<Livro> specs = Specification
        //        .where(LivroSpecs.isbnEqual(isbn))
         //       .and(LivroSpecs.tituloLike(titulo))
          //      .and(LivroSpecs.generoEqual(genero));

        //select * from livro where 0 = 0
        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction() );

        if(isbn != null){
            // query = query and isbn = :isbn
            specs = specs.and(isbnEqual(isbn));
        }

        if(titulo != null){
            specs = specs.and(tituloLike(titulo));
        }

        if(genero != null){
            // query = query and isbn = :isbn
            specs = specs.and(generoEqual(genero));
        }

        if(anoPublicacao != null){
            // query = query and isbn = :isbn
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if(nomeAutor != null){
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        return repository.findAll(specs);
    }

    public void atualizar(Livro livro) {
        if(livro.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o livro já esteja salvo na base.");
        }

        validator.validar(livro);
        repository.save(livro);
    }
}
