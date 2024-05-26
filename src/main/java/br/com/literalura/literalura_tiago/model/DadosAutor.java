package br.com.literalura.literalura_tiago.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @JsonAlias("name") String name,
        @JsonAlias("birthYear") int birthYear,
        @JsonAlias("deathYear") Integer deathYear
) {
}
