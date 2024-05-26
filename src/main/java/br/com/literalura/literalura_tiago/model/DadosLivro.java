package br.com.literalura.literalura_tiago.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("id") int id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<Autor>authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("mediaType") String mediaType,
        @JsonAlias("formats") Map<String, String>formats,
        @JsonAlias("downloadCount") int downloadCount
) {
}
