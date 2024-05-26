package br.com.literalura.literalura_tiago.repository;

import br.com.literalura.literalura_tiago.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Optional<Autor> findByName(String name);
}
