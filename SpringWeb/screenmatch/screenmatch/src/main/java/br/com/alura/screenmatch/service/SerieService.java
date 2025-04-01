package br.com.alura.screenmatch.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.enums.Categoria;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository serieRepository;

	public List<SerieDTO> getSeries() {
		return converteSerieToDto(serieRepository.findAll());
	}
	
	 public List<SerieDTO> obterTop5Series() {
	        return converteSerieToDto(serieRepository.findTop5ByOrderByAvaliacaoDesc());
	    }

	public List<SerieDTO> obterLancamentos() {
		return converteSerieToDto(serieRepository.findTop5ByOrderByEpisodiosDataLancamentoDesc());
	}

	public SerieDTO obterPorId(Long id) {
		Optional<Serie> serie = serieRepository.findById(id);

		if (serie.isPresent()) {
			Serie s = serie.get();
			return new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeason(), s.getRating(), s.getCategory(),
					s.getActor(), s.getPoster(), s.getSynopsis());
		}
		return null;
	}
	
	public List<SerieDTO> obterSeriesPorCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteSerieToDto(serieRepository.findByGenero(categoria));
    }
	
	public List<EpisodioDTO> obterTemporadasPorNumero(Long id, Long numero) {
        return serieRepository.obterEpisodiosPorTemporada(id, numero)
                .stream()
                .map(e -> new EpisodioDTO(e.getSeason(), e.getEpisodeNumebr(), e.getTitle()))
                .collect(Collectors.toList());
    }
	
	public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodes().stream()
                    .map(e -> new EpisodioDTO(e.getSeason(), e.getEpisodeNumebr(), e.getTitle()))
                    .collect(Collectors.toList());
        }
        return null;
    }
	
	public List<EpisodioDTO> obterTopEpisodios(Long id) {
        var serie = serieRepository.findById(id).get();
        return serieRepository.topEpisodiosPorSerie(serie)
                .stream()
                .map(e -> new EpisodioDTO(e.getSeason(), e.getEpisodeNumebr(), e.getTitle()))
                .collect(Collectors.toList());
    }

	private List<SerieDTO> converteSerieToDto(List<Serie> series) {
		return series.stream().map(s -> new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeason(), s.getRating(),
				s.getCategory(), s.getActor(), s.getPoster(), s.getSynopsis())).collect(Collectors.toList());
	}
}
