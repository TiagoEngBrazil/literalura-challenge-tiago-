package br.com.literalura.literalura_tiago.service;

import br.com.literalura.literalura_tiago.model.Autor;
import br.com.literalura.literalura_tiago.model.Format;
import br.com.literalura.literalura_tiago.model.Livro;
import br.com.literalura.literalura_tiago.repository.AutorRepository;
import br.com.literalura.literalura_tiago.repository.LivroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private static final String GUTENDEX_API_URL = "https://gutendex.com/books/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void salvarLivrosAutores(String title) {
        String apiUrl = GUTENDEX_API_URL + "?search=" + title;
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        JSONObject responseObject = new JSONObject(response.getBody());
        JSONArray booksArray = responseObject.getJSONArray("results");

        if (booksArray.length() > 0) {
            JSONObject bookJson = booksArray.getJSONObject(0);

            Livro book = new Livro();
            if (bookJson.getString("title").length() > 255) {
                book.setTitle(bookJson.getString("title").substring(0, 255));
            } else {
                book.setTitle(bookJson.getString("title"));
            }

            book.setDownloadCount(bookJson.getDouble("download_count"));

            JSONArray languagesJson = bookJson.getJSONArray("languages");
            List<String> languages = new ArrayList<>();
            for (int l = 0; l < languagesJson.length(); l++) {
                languages.add(languagesJson.getString(l));
            }
            book.setLanguages(languages);

            List<Format> formats = new ArrayList<>();
            JSONObject formatsJson = bookJson.getJSONObject("formats");
            for (String key : formatsJson.keySet()) {
                String value = formatsJson.getString(key);
                formats.add(new Format(key, value, book));
            }
            book.setFormats(formats);

            livroRepository.save(book);

            JSONArray authorsArray = bookJson.getJSONArray("authors");
            for (int j = 0; j < authorsArray.length(); j++) {
                JSONObject authorJson = authorsArray.getJSONObject(j);
                String authorName = authorJson.getString("name");

                Optional<Autor> existingAuthor = autorRepository.findByName(authorName);
                Autor author;
                if (existingAuthor.isPresent()) {
                    author = existingAuthor.get();
                } else {
                    author = new Autor();
                    author.setName(authorJson.getString("name"));

                    if (authorJson.has("birth_year") && !authorJson.isNull("birth_year")) {
                        author.setBirthYear(authorJson.getInt("birth_year"));
                    } else {
                        author.setDeathYear(null);
                    }

                    if (authorJson.has("death_year") && !authorJson.isNull("death_year")) {
                        author.setDeathYear(authorJson.getInt("death_year"));
                    } else {
                        author.setDeathYear(null);
                    }

                    autorRepository.save(author);
                }

                book.getAuthors().add(author);
            }

            livroRepository.save(book);
            imprimirDetalhesLivros(book);
        } else {
            System.out.println("Nenhum livro encontrado com o título fornecido.");
        }
    }

    @Transactional
    public void imprimirTodosLivros() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro encontrado no banco de dados.");
        } else {
            for (Livro livro : livros) {
                imprimirDetalhesLivros(livro);
            }
        }
    }

    private void imprimirDetalhesLivros(Livro livro) {
        System.out.println("\n------------LIVRO------------");
        System.out.println("Título: " + (livro.getTitle() != null ? livro.getTitle() : "N/A"));

        if (livro.getAuthors() != null && !livro.getAuthors().isEmpty()) {
            for (Autor author : livro.getAuthors()) {
                System.out.println("Autores: " + author.getName());
            }
        } else {
            System.out.println("   - N/A");
        }

        if (livro.getLanguages() != null && !livro.getLanguages().isEmpty()) {
            for (String language : livro.getLanguages()) {
                System.out.println("Idiomas: " + language);
            }
        } else {
            System.out.println("Idiomas: N/A");
        }

        System.out.println("Número de Downloads: " + (livro.getDownloadCount() != null ? livro.getDownloadCount() : "N/A"));
        System.out.println("-----------------------------\n");
    }
}
