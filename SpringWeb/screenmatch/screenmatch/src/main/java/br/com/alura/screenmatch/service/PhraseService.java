package br.com.alura.screenmatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.PhraseDTO;
import br.com.alura.screenmatch.model.Phrase;
import br.com.alura.screenmatch.repository.PhraseRepository;

@Service
public class PhraseService {

    @Autowired
    private PhraseRepository phraseRepository;

    public PhraseDTO searchRandomPhrase() {
        Phrase phrase = phraseRepository.searchRandomPhrase();
        return new PhraseDTO(phrase.getTitle(), phrase.getPhrase(), phrase.getCharacter(), phrase.getPoster());
    }
}
