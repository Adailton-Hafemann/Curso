package br.com.alura.screenmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.screenmatch.model.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {
	
    @Query("SELECT f FROM Frase f order by function('RANDOM') LIMIT 1")
    Phrase searchRandomPhrase();
}
