package io.github.robledocastro.libraryapi.repository;

import io.github.robledocastro.libraryapi.model.Autor;
import io.github.robledocastro.libraryapi.model.GeneroLivro;
import io.github.robledocastro.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository.findById(UUID.fromString("a8c29763-337b-4767-9b0a-f7af23c595c3")).orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Mais um livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Autorzao");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 31));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void buscarLivroTest(){
        UUID id = UUID.fromString("e348d402-5d06-4f9d-8b12-313e84120445");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());

        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());

    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("A lenda do heroizinho");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest(){
        List<Livro> lista = repository.findByIsbn("90887-84874");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloEPrecoTest(){
        var preco = BigDecimal.valueOf(650.00);
        List<Livro> lista = repository.findByTituloAndPreco("O roubo da casa assombrada", preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }
}
