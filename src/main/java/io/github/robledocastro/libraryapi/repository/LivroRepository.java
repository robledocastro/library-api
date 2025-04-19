package io.github.robledocastro.libraryapi.repository;

import io.github.robledocastro.libraryapi.model.Autor;
import io.github.robledocastro.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */
public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // Query Methods
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    List<Livro> findByTituloOrPreco(String titulo, BigDecimal preco);

    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    List<Livro> findByTituloLike(String titulo);

    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

}
