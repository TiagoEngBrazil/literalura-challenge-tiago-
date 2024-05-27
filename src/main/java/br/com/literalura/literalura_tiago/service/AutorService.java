package br.com.literalura.literalura_tiago.service;

import br.com.literalura.literalura_tiago.dto.LivrosAutoresDTO;
import br.com.literalura.literalura_tiago.model.Autor;
import br.com.literalura.literalura_tiago.model.Livro;
import br.com.literalura.literalura_tiago.repository.AutorRepository;
import br.com.literalura.literalura_tiago.repository.LivroRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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

        int posicaoLivro = 1;
        for (LivrosAutoresDTO autorComLivros : autoresComLivros) {
            System.out.println("\n------------AUTOR------------");
            System.out.println("Autor: " + autorComLivros.getAutor().getName());
            for (Livro livro : autorComLivros.getLivros()) {
                System.out.println("Livro - " + posicaoLivro + " : " + livro.getTitle());
                posicaoLivro++;
            }
            System.out.println("-----------------------------\n");
        }
    }

    @Transactional
    public void autoresVivosAno(Integer year) {
        List<Autor> autores = autorRepository.findAll();

        // Se a lista de autores estiver vazia, imprimir uma mensagem e retornar
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado no banco de dados.");
            return;
        }

        boolean encontrouAutorVivo = false;

        // Iterar sobre cada autor para verificar se está vivo no ano fornecido
        for (Autor autor : autores) {
            // Verificar se o autor estava vivo no ano fornecido
            if ((autor.getBirthYear() != null && autor.getBirthYear() <= year) &&
                    (autor.getDeathYear() == null || autor.getDeathYear() >= year)) {
                encontrouAutorVivo = true;
                // Imprimir os detalhes do autor
                System.out.println("\n------------ AUTOR ------------");
                System.out.println("Nome: " + autor.getName());
                System.out.println("Ano de Nascimento: " + autor.getBirthYear());
                System.out.println("Ano de Falecimento: " + (autor.getDeathYear() != null ? autor.getDeathYear() : "Vivo"));

                // Buscar os livros associados a esse autor
                List<Livro> autoresLivros = autor.getLivrosEAutores();
                if (autoresLivros.isEmpty()) {
                    System.out.println("   - Nenhum livro encontrado.");
                } else {
                    int posicaoLivro = 1;
                    for (Livro livro : autoresLivros) {
                        System.out.println("Livro " + posicaoLivro + ": " + livro.getTitle());
                        posicaoLivro++;
                    }
                }
                System.out.println("------------------------------\n");
            }
        }

        // Se não encontrou nenhum autor vivo no ano fornecido
        if (!encontrouAutorVivo) {
            System.out.println("Nenhum autor vivo encontrado no ano " + year + ".");
        }
    }

}
