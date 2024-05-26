package br.com.literalura.literalura_tiago.service;

public interface IconverteDados {
    abstract <T> T obterDados(String json, Class<T> classe);
}
