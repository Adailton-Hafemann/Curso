package br.com.alura.screenmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.dto.PhraseDTO;
import br.com.alura.screenmatch.service.PhraseService;

@RestController
public class PhraseController {

    @Autowired
    private PhraseService servico;

    @GetMapping("/series/phrase")
    public PhraseDTO searchRandomPhrase() {
        return servico.searchRandomPhrase();
    }
}