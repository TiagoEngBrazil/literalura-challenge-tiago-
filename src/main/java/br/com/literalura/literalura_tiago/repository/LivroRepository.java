package br.com.literalura.literalura_tiago.repository;

import br.com.literalura.literalura_tiago.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
