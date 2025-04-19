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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){

        Autor autor = new Autor();
        autor.setNome("Autorzin");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){

        var id = UUID.fromString("a29bec96-db36-4415-84eb-6cfdc0decf9c");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);

        }
    }

    @Test
    public void listaTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());

    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("a29bec96-db36-4415-84eb-6cfdc0decf9c");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("097ab6a4-5323-4010-8f6a-a2ddf2e67c91");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){

        Autor autor = new Autor();
        autor.setNome("JOãozinho");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1988, 1, 31));

        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(239));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("A lenda do heroizinho");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90000-12345");
        livro2.setPreco(BigDecimal.valueOf(399));
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setTitulo("A lenda do heroizinho - part II");
        livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        //livroRepository.saveAll(autor.getLivros());

    }

    @Test
    void listaLivrosAutor(){
        var id = UUID.fromString("7a8754eb-e47c-4808-94e0-bbad146742c2");
        var autor = repository.findById(id).get();

        //Busca os livros do autor
        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }

}
