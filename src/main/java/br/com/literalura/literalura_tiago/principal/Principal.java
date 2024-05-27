package br.com.literalura.literalura_tiago.principal;

import br.com.literalura.literalura_tiago.model.Autor;
import br.com.literalura.literalura_tiago.model.DadosLivro;
import br.com.literalura.literalura_tiago.model.Livro;
import br.com.literalura.literalura_tiago.repository.LivroRepository;
import br.com.literalura.literalura_tiago.service.AutorService;
import br.com.literalura.literalura_tiago.service.ConsumoApi;
import br.com.literalura.literalura_tiago.service.ConverteDados;
import br.com.literalura.literalura_tiago.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
                6 - Listar Top 10 livros mais baixados
                
                 0 - Sair                                 
                 """;

        int opcao = -1;

        while (opcao != 0) {
            System.out.println(menu);

            try {
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(leitura.nextLine());

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
                    case 6 -> {
                        System.out.println("Opção 6 selecionada");
                        top10Downloads();
                    }
                    case 0 -> {
                        System.out.println("Saindo ...");
                    }
                    default -> {
                        System.out.println("Opção inválida");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
            }
        }
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
        System.out.print("Insira o ano que deseja pesquisar: ");
        var ano = leitura.nextInt();

        autorService.autoresVivosAno(ano);
    }

    private void buscarLivrosPorIdioma() {
        System.out.println("Insira o idioma para realizar a busca: ");
        System.out.print("\nes - espanhol" + "\n" + "en - inglês" + "\n" + "fr - francês" + "\n" + "pt - português\n");
        var idioma = leitura.nextLine();

        livroService.listarLivrosPeloIdioma(idioma);
    }

    @Transactional
    private void top10Downloads() {
        livroService.exibirTop10Downloads();
    }
}
