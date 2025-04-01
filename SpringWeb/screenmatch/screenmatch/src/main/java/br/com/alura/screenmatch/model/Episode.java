package br.com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodios")
public class Episode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="temporada")
	private Integer season;
	
	@Column(name="titulo")
	private String title;
	
	@Column(name="numeroEpisodio")
	private Integer episodeNumebr;
	
	@Column(name="avaliacao")
	private Double rating;
	
	@Column(name="dataLancamento")
	private LocalDate releaseDate;
	
	@ManyToOne
	private Serie serie;

	public Episode() {
	}

	public Episode(Integer season, EpisodeData episodeData) {
		this.season = season;
		this.title = episodeData.title();
		this.episodeNumebr = episodeData.episode();

		try {
			this.rating = Double.valueOf(episodeData.imdbRating());
		} catch (NumberFormatException ex) {
			this.rating = 0.0;
		}

		try {
			this.releaseDate = LocalDate.parse(episodeData.released());
		} catch (DateTimeParseException ex) {
			this.releaseDate = null;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getEpisodeNumebr() {
		return episodeNumebr;
	}

	public void setEpisodeNumebr(Integer episodeNumebr) {
		this.episodeNumebr = episodeNumebr;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	@Override
	public String toString() {
		return "temporada=" + season + ", titulo='" + title + '\'' + ", numeroEpisodio=" + episodeNumebr
				+ ", avaliacao=" + rating + ", dataLancamento=" + releaseDate;
	}
}