package br.com.literalura.literalura_tiago.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumoApi {

    private final RestTemplate restTemplate;
    

    public ConsumoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obterDados(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
