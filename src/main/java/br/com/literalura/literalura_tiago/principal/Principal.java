package br.com.literalura.literalura_tiago.principal;

import br.com.literalura.literalura_tiago.modelo.Livro;
import br.com.literalura.literalura_tiago.repositorio.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private CosumoApi consumo = new CosumoApi();

    private ConverteDados conversor = new ConverteDados();


    private final String ENDERECO = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=888b7841";


    @Autowired
    private LivroRepository repositorio;

    private List<Livro> livros = new ArrayList<>();

    Optional<Serie> serieBusca;

    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {

        var menu = """
                 \n
                 1 - Buscar séries
                 2 - Buscar episódios
                 3 - Listar series buscadas
                 4 - Buscar séries por título 
                 5 - Buscar séries por ator  
                 6 - Buscar séries por avaliação  
                 7 - Buscar séries por categoria  
                 8 - Buscar séries por temporadas e melhor avaliação  
                 9 - Buscar séries por episódio  
                10 - Buscar séries por título e aprsentar os top 5 
                11 - Buscar episódios depois de uma data
                
                 0 - Sair                                 
                 """;

        System.out.println(menu);
        var opcao = leitura.nextInt();
        leitura.nextLine();


        while (opcao != 0) {
            if (opcao == 1) {
                buscarSerieWeb();
            } else if (opcao == 2) {
                buscarEpisodioPorSerie();
            } else if (opcao == 3) {
                listarSerieBuscadas();
            } else if (opcao == 4) {
                buscarEpisodioPorTitulo();
            } else if (opcao == 5) {
                buscarSeriePorAtor();
            } else if (opcao == 6) {
                buscarTop5Series();
            } else if (opcao == 7) {
                buscarSeriePorCategoria();
            } else if (opcao == 8) {
                buscarSeriePorTotalTemporadas();
            } else if (opcao == 9) {
                buscarSeriePorTrecho();
            } else if (opcao == 10) {
                topEpsodiosPorSerie();
            } else if (opcao == 11) {
                buscarEpisodiosPosData();
            } else {
                System.out.println("Opção inválida");
            }
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
        }
        System.out.println("Saindo ...");
    }
}
