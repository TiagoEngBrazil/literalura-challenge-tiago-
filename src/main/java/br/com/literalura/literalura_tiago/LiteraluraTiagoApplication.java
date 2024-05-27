package br.com.literalura.literalura_tiago;

import br.com.literalura.literalura_tiago.principal.Principal;
import br.com.literalura.literalura_tiago.service.AutorService;
import br.com.literalura.literalura_tiago.service.ConsumoApi;
import br.com.literalura.literalura_tiago.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LiteraluraTiagoApplication implements CommandLineRunner {

	private final ConsumoApi consumoApi;

	private final LivroService livroService;

	private final AutorService autorService;

	public LiteraluraTiagoApplication(@Lazy ConsumoApi consumoApi, LivroService livroService, AutorService autorService) {
		this.consumoApi = consumoApi;
        this.livroService = livroService;
        this.autorService = autorService;
    }

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraTiagoApplication.class, args);
	}

	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(consumoApi, livroService, autorService);

		principal.exibeMenu();
	}
}
