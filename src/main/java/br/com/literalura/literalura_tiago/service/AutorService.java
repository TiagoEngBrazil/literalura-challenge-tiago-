package br.com.literalura.literalura_tiago.service;

import br.com.literalura.literalura_tiago.dto.LivrosAutoresDTO;
import br.com.literalura.literalura_tiago.model.Autor;
import br.com.literalura.literalura_tiago.model.Livro;
import br.com.literalura.literalura_tiago.repository.AutorRepository;
import br.com.literalura.literalura_tiago.repository.LivroRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    @Autowired
    private final AutorRepository autorRepository;

    @Autowired
    private final LivroRepository livroRepository;

    @Autowired
    private final EntityManager entityManager;

    @Autowired
    public AutorService(AutorRepository autorRepository, LivroRepository livroRepository, EntityManager entityManager) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
        this.entityManager = entityManager;
    }

    public List<LivrosAutoresDTO> getTodosAutoresLivros() {
        List<Autor> autores = autorRepository.findAll();
        return autores.stream().map(autor -> {
            List<Livro> livros = livroRepository.findByAuthorsContaining(autor);
            return new LivrosAutoresDTO(autor, livros);
        }).collect(Collectors.toList());
    }

    public void telaAutoresLivros() {
        List<LivrosAutoresDTO> autoresComLivros = getTodosAutoresLivros();
        for (LivrosAutoresDTO autorComLivros : autoresComLivros) {
            System.out.println("\n------------AUTOR------------");
            System.out.println("Autor: " + autorComLivros.getAutor().getName());
            for (Livro livro : autorComLivros.getLivros()) {
                System.out.println("Livros: " + livro.getTitle());
            }
            System.out.println("-----------------------------\n");
        }
    }
}
