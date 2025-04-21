package io.github.robledocastro.libraryapi.repository.specs;

import io.github.robledocastro.libraryapi.model.GeneroLivro;
import io.github.robledocastro.libraryapi.model.Livro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    public static Specification<Livro> isbnEqual(String isbn){
        return (root, query, cb) ->
                cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo){
        // like(livro,titulo) like %:param%)
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Livro> generoEqual(GeneroLivro genero){
        return (root, query, cb) ->
                cb.equal(root.get("genero"), genero);
    }

    public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao){
        // and to_char(data_publicacao, 'YYYY') = :anoPublicacao
        return (root, query, cb) ->
                cb.equal(cb.function("to_char", String.class,
                        root.get("dataPublicacao"),cb.literal("YYYY")), anoPublicacao.toString());
    }

    public static Specification<Livro> nomeAutorLike(String nome){
        return (root, query, cb) -> {
            // especificando o join
            Join<Object, Object> joinAutor = root.join("autor", JoinType.LEFT);
            return cb.like( cb.upper(joinAutor.get("nome")), "%" + nome.toUpperCase() + "%" );

            // outra forma de fazer
            //return cb.like( cb.upper(root.get("autor").get("nome")), "%" + nome.toUpperCase() + "%" );
        };
    }
}
