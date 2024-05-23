package br.com.literalura.literalura_tiago;

import br.com.literalura.literalura_tiago.repositorio.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraTiagoApplication {

	@Autowired
	private LivroRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraTiagoApplication.class, args);
	}

	@Override

}
