package br.com.literalura.literalura_tiago.principal;

import br.com.literalura.literalura_tiago.model.DadosLivro;
import br.com.literalura.literalura_tiago.model.Livro;
import br.com.literalura.literalura_tiago.service.AutorService;
import br.com.literalura.literalura_tiago.service.ConsumoApi;
import br.com.literalura.literalura_tiago.service.ConverteDados;
import br.com.literalura.literalura_tiago.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final Scanner leitura = new Scanner(System.in);

    private final ConverteDados conversor = new ConverteDados();

    private final ConsumoApi consumoApi;

    private final LivroService livroService;

    private final AutorService autorService;

    private List<DadosLivro> dadosLivros = new ArrayList<>();

    @Autowired
    public Principal(ConsumoApi consumoApi, LivroService livroService, AutorService autorService) {
        this.consumoApi = consumoApi;
        this.livroService = livroService;
        this.autorService = autorService;
    }

    private final String ENDERECO = "http://gutendex.com/books?search=";

    public void exibeMenu() {
        var menu = """
                 \n
                Digite a opção desejada:
                1 - Buscar livros pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar por autores que estavam vivos em um determinado ano 
                5 - Listar livros em um determinado idioma  
                
                 0 - Sair                                 
                 """;

        System.out.println(menu);
        var opcao = leitura.nextInt();
        leitura.nextLine();

        while (opcao != 0) {
            switch (opcao) {
                case 1 -> {
                    System.out.println("Opção 1 selecionada");
                    buscarLivroPeloTitulo();
                }
                case 2 -> {
                    System.out.println("Opção 2 selecionada");
                    listarLivrosRegis();
                }
                case 3 -> {
                    System.out.println("Opção 3 selecionada");
                    listarAutoresRegis();
                }
                case 4 -> {
                    System.out.println("Opção 4 selecionada");
                    autoresVivosEmAno();
                }
                case 5 -> {
                    System.out.println("Opção 5 selecionada");
                    buscarLivrosPorIdioma();
                }
                default -> System.out.println("Opção inválida");
            }
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
        }
        System.out.println("Saindo ...");
        System.exit(0);
    }

    private void buscarLivroPeloTitulo() {
        System.out.println("Digite o nome do livro que deseja buscar: ");
        var nomeLivro = leitura.nextLine();

        livroService.salvarLivrosAutores(nomeLivro);
    }

    private void listarLivrosRegis() {
        livroService.imprimirTodosLivros();
    }

    private void listarAutoresRegis() {
        autorService.telaAutoresLivros();
    }

    private void autoresVivosEmAno() {
        // Implementação do método
    }

    private void buscarLivrosPorIdioma() {
        // Implementação do método
    }
}
