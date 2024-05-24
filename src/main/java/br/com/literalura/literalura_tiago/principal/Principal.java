package br.com.literalura.literalura_tiago.principal;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

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
                    listarLivrosRegis();
                }
                case 4 -> {
                    System.out.println("Opção 4 selecionada");
                    atoresVivosEmAno();
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
    }

    private void buscarLivroPeloTitulo() {

    }

    private void listarLivrosRegis() {

    }

    private void atoresVivosEmAno() {

    }

    private void buscarLivrosPorIdioma() {

    }


}
