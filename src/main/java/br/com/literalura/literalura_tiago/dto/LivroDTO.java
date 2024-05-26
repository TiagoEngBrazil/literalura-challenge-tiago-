package br.com.literalura.literalura_tiago.dto;

import br.com.literalura.literalura_tiago.model.Autor;

import java.util.List;
import java.util.Map;

public record LivroDTO(
        int id,
        String title,
        List<Autor>authors,
        List<Autor> translators,
        List<String> subjects,
        List<String> bookshelves,
        List<String> languages,
        boolean copyright,
        String mediaType,
        Map<String, String>formats,
        int downloadCount
) {}
