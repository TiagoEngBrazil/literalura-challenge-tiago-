package br.com.literalura.literalura_tiago.service;

import br.com.literalura.literalura_tiago.LiteraluraTiagoApplication;
import org.springframework.stereotype.Service;

@Service
public class SomeService {
    private final LiteraluraTiagoApplication literaluraTiagoApplication;

    public SomeService(LiteraluraTiagoApplication literaluraTiagoApplication) {
        this.literaluraTiagoApplication = literaluraTiagoApplication;
    }

    // Métodos da classe
}

