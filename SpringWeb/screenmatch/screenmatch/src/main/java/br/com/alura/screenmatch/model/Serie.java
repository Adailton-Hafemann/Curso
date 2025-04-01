package br.com.alura.screenmatch.model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import br.com.alura.screenmatch.enums.Categoria;
import br.com.alura.screenmatch.util.ConsultaChatGPT;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "series")
public class Serie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="titulo")
	private String title;
	
	@Column(name="totalTemporadas")
	private Integer totalSeason;
	
	@Column(name="avaliacao")
	private Double rating;
	
	@Enumerated(EnumType.STRING)
	@Column(name="genero")
	private Categoria category;
	
	@Column(name="atores")
	private String actor;
	
	@Column(name="poster")
	private String poster;
	
	@Column(name="sinopse")
	private String synopsis;

	@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Episode> episodes = new ArrayList<>();

	public Serie() {
	}

	public Serie(DadosSerie dadosSerie) {
		this.title = dadosSerie.titulo();
		this.totalSeason = dadosSerie.totalTemporadas();
		this.rating = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
		this.category = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
		this.actor = dadosSerie.atores();
		this.poster = dadosSerie.poster();
		this.synopsis = ConsultaChatGPT.obterTraducao(dadosSerie.sinopse()).trim();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		episodes.forEach(e -> e.setSerie(this));
		this.episodes = episodes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTotalSeason() {
		return totalSeason;
	}

	public void setTotalSeason(Integer totalSeason) {
		this.totalSeason = totalSeason;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Categoria getCategory() {
		return category;
	}

	public void setCategory(Categoria category) {
		this.category = category;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public String toString() {
		return "genero=" + category + ", titulo='" + title + '\'' + ", totalTemporadas=" + totalSeason
				+ ", avaliacao=" + rating + ", atores='" + actor + '\'' + ", poster='" + poster + '\''
				+ ", sinopse='" + synopsis + '\'' + ", episodios='" + episodes + '\'';
	}
}
