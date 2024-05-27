package br.com.literalura.literalura_tiago.repository;

import br.com.literalura.literalura_tiago.model.Autor;
import br.com.literalura.literalura_tiago.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    @Transactional
    @Override
    List<Livro> findAll();

    List<Livro> findByAuthorsContaining(Autor autore);
}
