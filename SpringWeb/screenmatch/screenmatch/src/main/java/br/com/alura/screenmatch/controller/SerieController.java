package br.com.alura.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.açura.screenmatch.service.SerieService;

@RestController
public class SerieController {
	
	@Autowired
	private SerieService serieService;

	@GetMapping("/series")
    public List<SerieDTO> obterSeries() {
        return serieService.getSeries();
    }
	
}
